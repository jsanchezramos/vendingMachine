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

    private VendingMachine vendingMachine;
    private CoinsService coinsService ;
    private ProductService productService;
    private Wallet wallet;


    public VendingServiceImpl(CoinsService coinsService,ProductService productService) {
        this.coinsService = coinsService;
        this.productService = productService;
    }

    private List<Coin> accepCoins(final String coinString){
        List<Coin> lCoins =  coinsService.parseCoins(coinString);

        final List<Coin> acceptable = lCoins.stream().filter(Coin::isValid).collect(Collectors.toList());
        final List<Coin> rejected   = lCoins.stream().filter(coin -> !coin.isValid()).collect(Collectors.toList());

        wallet = new Wallet(acceptable);
        return rejected;
    }

    @Override
    public Boolean upServiceMachine(final String coinString){
        try{
            List<Coin> rejected = accepCoins(coinString);

            if(wallet.getAvailable().isEmpty()){
                throw new NotValidMoneyException();
            }

            vendingMachine = new VendingMachine(productService.getAllProductStock(),wallet);

            if(rejected.size()>0)log().info("returning coins: {}", rejected);

            log().info("Ok up Service Machine");
            return true;
        }catch(Exception ex){
            log().error("Error up Service Machine",ex);
            return false;
        }
    }

    @Override
    public Boolean addNewProductService(Map<Product, Integer> product) {
        return null;
    }


}
