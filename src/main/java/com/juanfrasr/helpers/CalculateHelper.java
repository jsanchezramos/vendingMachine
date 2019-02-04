package com.juanfrasr.helpers;


import com.juanfrasr.model.Coin;

import java.util.ArrayList;
import java.util.List;

public class CalculateHelper {

    private CalculateHelper() {
    }

    private static CalculateHelper instance;

    public static synchronized CalculateHelper getInstance(){
        if(instance == null){
            instance = new CalculateHelper();
        }
        return instance;
    }


    public List<Coin> calculate(double returnCoin, List<Coin> validCoins)
    {
        double total = 0;
        List<Coin> outCoin = new ArrayList<>();

        while (total<returnCoin && !validCoins.isEmpty()) {
            double x = validCoins.get(0).getValue();
            if ((total+x) <= returnCoin) {
                outCoin.add(validCoins.get(0));
                total= total + x;
            } else {
                validCoins.remove(0);
            }
        }
        return outCoin;
    }




}
