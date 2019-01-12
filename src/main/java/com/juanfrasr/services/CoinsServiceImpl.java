package com.juanfrasr.services;

import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.model.Coin;

import java.util.*;
import java.util.stream.Collectors;

public class CoinsServiceImpl implements CoinsService , ILogger{

    private static final Map<String,Coin> SUPPORTED_COIN_MAP = new HashMap<>();

    static {
        SUPPORTED_COIN_MAP.put("0.05€",new Coin("5 centimos",0.05,true));
        SUPPORTED_COIN_MAP.put("0.10€",new Coin("10 centimos",0.10,true));
        SUPPORTED_COIN_MAP.put("0.20€",new Coin("20 centimos",0.20,true ));
        SUPPORTED_COIN_MAP.put("0.50€",new Coin("50 centimos",0.50,true ));
        SUPPORTED_COIN_MAP.put("1€",new Coin("1 euro",1,true ));
        SUPPORTED_COIN_MAP.put("2€",new Coin("2 euros",2,true ));
    }

    @Override
    public List<Coin> parseCoins(String coinString) {
        List<Coin> coinz = Collections.emptyList();
        final String trimString = coinString.trim();

        if(!"".equals(trimString)){
            coinz = Arrays.asList(coinString.trim().split("\\s+")).stream().map(this::findCoinType).collect(Collectors.toList());
        }

        return Collections.unmodifiableList(coinz);
    }

    private Coin findCoinType(final String centValue) {
        Coin coin = SUPPORTED_COIN_MAP.get(centValue);

        if (coin == null) {
            coin = new Coin(centValue,0,false);
        }
        return coin;
    }
}
