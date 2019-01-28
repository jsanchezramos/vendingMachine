package com.juanfrasr.vending.helper;

import com.juanfrasr.helpers.CalculateHelper;
import com.juanfrasr.model.Coin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Test to validate logicate to return coins
 */
public class CalculateTest {

    /**
     * return corrent coins to expecific price
     */
    @Test
    public void returnCorrectCoins(){
        CalculateHelper calculateHelper = CalculateHelper.getInstance();

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(new Coin("2 euro",2,true));
        validCoins.add(new Coin("1 euro",1,true));
        validCoins.add(new Coin("0.30 euro",0.30,true));

        List<Coin> returnCoin = calculateHelper.calculate(3,validCoins);

        assertTrue(returnCoin.size()==2);
        assertTrue(returnCoin.get(0).getValue() == 2);
        assertTrue(returnCoin.get(1).getValue() == 1);
    }

}
