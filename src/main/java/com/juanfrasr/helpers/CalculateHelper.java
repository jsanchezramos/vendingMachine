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


    public List<Coin> calculate(double importe, List<Coin> lCoins)
    {
        double total = 0;
        List<Coin> outCoin = new ArrayList<>();

        while (total<importe && !lCoins.isEmpty()) {
            double x = lCoins.get(0).getValue();
            if ((total+x) <= importe) {
                outCoin.add(lCoins.get(0));
                total= total + x;
            } else {
                lCoins.remove(0);
            }
        }
        return outCoin;
    }




}
