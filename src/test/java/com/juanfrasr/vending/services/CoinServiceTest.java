package com.juanfrasr.vending.services;

import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.model.Bank;
import com.juanfrasr.model.Coin;
import com.juanfrasr.services.CoinsServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Test logical CoinService
 */
public class CoinServiceTest {

    private CoinsService coinsService = new CoinsServiceImpl();
    private List<Coin> lCoin = new ArrayList<>();

    @Before
    public void loadDummyCoin(){
        lCoin.add(new Coin("1 euro",1,true));
    }

    /**
     * Check Correct coints
     */
    @Test
    public void parseCoinTest(){
        String valueCoin = "1,50€ 2€";
        List<Coin> lCoin = coinsService.parseCoins(valueCoin);
        assertTrue(lCoin.size()==2);
    }

    /**
     * add coin to list coins
     */
    @Test
    public void addCoinTest(){
        assertTrue(lCoin.size() == 1);

        lCoin = coinsService.addCoin(lCoin,"2€");
        assertTrue(lCoin.size()==2);

    }
    /**
     * Return valid coin to specific price
     */
    @Test
    public void returnPriceTest(){
        List<Coin> returnCoin = coinsService.returnCoin(2.30);
        assertTrue(returnCoin.get(0).getValue() == 2 );
        assertTrue(returnCoin.get(1).getValue() == 0.20 );
        assertTrue(returnCoin.get(2).getValue() == 0.05 );
        assertTrue(returnCoin.get(3).getValue() == 0.05 );
    }
    /**
     * Insert coin to increase bank
     */
    @Test
    public void insertCointTest(){
        Bank bank = new Bank(lCoin);
        assertTrue(bank.getTotal() == 1);

        List<Coin> lCoinadd = new ArrayList<>();
        lCoinadd.add(new Coin("2 euro",2,true));

        bank = coinsService.insertCoin(lCoinadd,bank);
        assertTrue(bank.getTotal() == 3);
    }
}
