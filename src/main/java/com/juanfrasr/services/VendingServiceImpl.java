package com.juanfrasr.services;

import com.juanfrasr.exceptions.NotMoneyException;
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
        try{
            List<Coin> rejected = validateCoins(coinString);

            if(bank.getAvailable().isEmpty()){
                throw new NotMoneyException();
            }

            vendingMachine = new VendingMachine(productService.getAllProductStock(), bank);

            if(rejected.size()>0)log().info("returning coins: {}", rejected);

            if(vendingMachine != null){
                log().info("Ok up Service Machine");
                statusVending = true;
            }
        }catch(Exception ex){
            log().error("Error up Service Machine",ex);
            return false;
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
    public void sellProduct(Product product) {
        ProductStock productStock = productService.returnProduct(product);
        log().info("stock product : "+ productStock.getQuantity());
        log().info("cash: "+bank.getTotal());

        double change = bank.getTotal() - productStock.getProduct().getPrice();
        List<Coin> lCoin = coinsService.returnCoin(bank.getAvailable(),change);

        bank.setAvailable(lCoin);

        log().info( " Total cash to return productg "+ bank.getTotal());
    }

    @Override
    public void addCoinVending(String coinString) {
        List<Coin> lCoin = bank.getAvailable();
        bank.setAvailable(coinsService.addCoin(lCoin,coinString));
    }

    @Override
    public double getCash() {
        return bank.getTotal();
    }
}
