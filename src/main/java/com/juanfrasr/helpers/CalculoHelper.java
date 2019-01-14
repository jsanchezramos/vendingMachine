package com.juanfrasr.helpers;


import com.juanfrasr.model.Coin;

import java.util.ArrayList;
import java.util.List;

public class CalculoHelper {

    private static CalculoHelper instance;

    public static synchronized CalculoHelper getInstance(){
        if(instance == null){
            instance = new CalculoHelper();
        }
        return instance;
    }


    public List<Coin> calcular(double importe, List<Coin> lCoins)
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
