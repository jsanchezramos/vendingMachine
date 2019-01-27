package com.juanfrasr.services;

import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.interfaces.VendingService;
import com.juanfrasr.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * VendingServiceImpl to service vending machine.
 */

public class VendingServiceImpl implements VendingService, ILogger {

    private VendingMachine vendingMachine = null;
    private CoinsService coinsService ;
    private ProductService productService;
    private Bank bank;
    private Boolean statusVending = false;


    public VendingServiceImpl(CoinsService coinsService,ProductService productService) {
        this.coinsService = coinsService;
        this.productService = productService;
    }

    private List<Coin> validateCoins(final String coinString){
        List<Coin> lCoins =  coinsService.parseCoins(coinString);

        final List<Coin> acceptable = lCoins.stream().filter(Coin::isValid).collect(Collectors.toList());
        final List<Coin> rejected   = lCoins.stream().filter(coin -> !coin.isValid()).collect(Collectors.toList());

        bank = new Bank(acceptable);
        double sum = acceptable.stream().mapToDouble(c->c.getValue()).sum();
        bank.setTotal(sum);

        return rejected;
    }

    @Override
    public Boolean startVendingMachine(final String coinString){
        try {
            List<Coin> rejected = validateCoins(coinString);
            statusVending = false;

            if (!bank.getAvailable().isEmpty()) {
                vendingMachine = new VendingMachine(productService.getAllProductStock(), bank);

                if (rejected.size() > 0)log().info("returning coins: {}", rejected.size());

                if (vendingMachine != null) {
                    log().info("Ok start Service Machine");
                    statusVending = true;
                }
            }else{
                log().info("Not money, not start machine");
            }
        } catch(Exception ex){
            log().error("Error start Service Machine ", ex);
        }
        return statusVending;
    }

    @Override
    public Boolean addNewProductVending(Map<Product, Integer> product) {
        try{
            productService.addProductsInStock(product);
            log().info("Ok add new product in stock");
            return true;
        }catch(Exception ex){
            log().error("Error: ",ex);
            return false;
        }
    }

    @Override
    public Boolean sellProduct(Product product) {
        Boolean ok = false;
        ProductStock productStock = productService.returnProductStock(product);
        log().info("stock product : "+ productStock.getQuantity());

        if(productStock != null && productStock.getQuantity()>0 && bank != null && bank.getTotal()>= product.getPrice()){
            log().info("cash: "+bank.getTotal());
            double change = bank.getTotal() - productStock.getProduct().getPrice();
            List<Coin> lCoin = coinsService.returnCoin(change);

            bank.setAvailable(lCoin);

            log().info( "Product change "+ bank.getTotal());
            ok = true;
        }else{
            log().info("not sufficient money or no stock product");
        }
        return ok;
    }

    @Override
    public void addCoinVending(String coinString) {
        List<Coin> lCoin = bank.getAvailable();
        bank.setAvailable(coinsService.addCoin(lCoin,coinString));
    }

    @Override
    public double getCoinCurrent() {
        return bank.getTotal();
    }
}
