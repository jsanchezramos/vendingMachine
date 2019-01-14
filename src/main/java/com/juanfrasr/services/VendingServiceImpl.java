package com.juanfrasr.services;

import com.juanfrasr.exceptions.NotValidMoneyException;
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

    private List<Coin> accepCoins(final String coinString){
        List<Coin> lCoins =  coinsService.parseCoins(coinString);

        final List<Coin> acceptable = lCoins.stream().filter(Coin::isValid).collect(Collectors.toList());
        final List<Coin> rejected   = lCoins.stream().filter(coin -> !coin.isValid()).collect(Collectors.toList());

        bank = new Bank(acceptable);

        return rejected;
    }

    @Override
    public Boolean upServiceMachine(final String coinString){
        try{
            List<Coin> rejected = accepCoins(coinString);

            if(bank.getAvailable().isEmpty()){
                throw new NotValidMoneyException();
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
}
