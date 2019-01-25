package com.juanfrasr.vending;

import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.model.Bank;
import com.juanfrasr.model.Coin;
import com.juanfrasr.model.Product;
import com.juanfrasr.services.CoinsServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Test coin
 */

public class CoinTest {

    /**
     * Return all coins to cancel product
     */
    @Test
    public void refundCoin(){
        CoinsService coinsService = new CoinsServiceImpl();

        List<Coin> lCoins = new ArrayList<>();
        Product product = new Product("Coke",0.90);

        List<Coin> lCoin = coinsService.returnCoin(lCoins,product.getPrice());

        assertTrue(lCoin.get(0).getValue() == 0.50);
        assertTrue(lCoin.get(1).getValue() == 0.20);
        assertTrue(lCoin.get(2).getValue() == 0.20);
    }

    /**
     * Refill coins to bank vending.
     */
    @Test
    public void refillCoins(){
        CoinsService coinsService =  new CoinsServiceImpl();

        List<Coin> lCoin = new ArrayList<>();
        lCoin.add(new Coin("50 centimos",0.50,true));
        Bank bank = new Bank(lCoin);
        bank.setTotal(0.50);

        List<Coin> lCoinRefill = new ArrayList<>();
        lCoinRefill.add(new Coin("2 euros",2,true));

        bank = coinsService.insertCoin(lCoinRefill,bank);

        assertTrue(bank.getTotal() != 0.50);
        assertTrue(bank.getAvailable().size()>1);
    }

}
