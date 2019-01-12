package com.juanfrasr.services;

import com.juanfrasr.exceptions.NotValidMoneyException;
import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.interfaces.VendingService;
import com.juanfrasr.model.Coin;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.model.VendingMachine;
import com.juanfrasr.model.Wallet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * VendingServiceImpl to service vending machine.
 */
public class VendingServiceImpl implements VendingService {

    private VendingMachine vendingMachine;
    private CoinsService coinsService ;
    private Wallet wallet;


    public VendingServiceImpl(CoinsService coinsService) {
        this.coinsService = coinsService;
    }

    private void accepCoins(final String coinString){
        List<Coin> lCoins =  coinsService.parseCoins(coinString);

        final List<Coin> acceptable = lCoins.stream().filter(Coin::isValid).collect(Collectors.toList());
        final List<Coin> rejected   = lCoins.stream().filter(coin -> !coin.isValid()).collect(Collectors.toList());

        wallet = new Wallet(acceptable);
    }

    @Override
    public Boolean upServiceMachine(final String coinString, final List<ProductStock> lStockProduct){
        try{
            accepCoins(coinString);

            if(wallet.getAvailable().isEmpty()){
                throw new NotValidMoneyException();
            }

            vendingMachine = new VendingMachine(lStockProduct,wallet);

        }catch(Exception ex){
            System.out.print(ex);
            return false;
        }
        return true;
    }


}
