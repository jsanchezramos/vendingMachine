package com.juanfrasr.services;

import com.juanfrasr.helpers.CalculoHelper;
import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.model.Bank;
import com.juanfrasr.model.Coin;
import com.juanfrasr.model.Product;

import java.util.*;
import java.util.stream.Collectors;

public class CoinsServiceImpl implements CoinsService , ILogger{

    private static final Map<String,Coin> SUPPORTED_COIN_MAP = new HashMap<>();
    private CalculoHelper calculoHelper = CalculoHelper.getInstance();

    static {
        SUPPORTED_COIN_MAP.put("2€",new Coin("2 euros",2,true ));
        SUPPORTED_COIN_MAP.put("1€",new Coin("1 euro",1,true ));
        SUPPORTED_COIN_MAP.put("0.50€",new Coin("50 centimos",0.50,true ));
        SUPPORTED_COIN_MAP.put("0.20€",new Coin("20 centimos",0.20,true ));
        SUPPORTED_COIN_MAP.put("0.10€",new Coin("10 centimos",0.10,true));
        SUPPORTED_COIN_MAP.put("0.05€",new Coin("5 centimos",0.05,true));
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

    @Override
    public List<Coin> addCoinToList(List<Coin> lCoins, String coin) {
        final String trimString = coin.trim();

        if(!"".equals(trimString)){
            Coin coinObject = findCoinType(trimString);
            lCoins.add(coinObject);
        }

        return lCoins;
    }

    @Override
    public List<Coin> refundCoin(List<Coin> lCoin, Product product){
        Collection<Coin> lCoins = SUPPORTED_COIN_MAP.values();

        List<Coin> result = lCoins.stream().sorted((p1,p2) -> Double.compare(p2.getValue(), p1.getValue())).collect(Collectors.toList());
        List<Coin> refundCoind = calculoHelper.calcular(product.getPrice(), result);

        return refundCoind;
    }

    @Override
    public Bank refillCoin(List<Coin> lCoins, Bank bank) {
        double sum = lCoins.stream().mapToDouble(c->c.getValue()).sum();

        bank.setTotal(bank.getTotal() + sum);
        bank.getAvailable().stream().collect(Collectors.toCollection(()-> lCoins));
        bank.setAvailable(lCoins);

        return bank;
    }

    private Coin findCoinType(final String centValue) {
        Coin coin = SUPPORTED_COIN_MAP.get(centValue);

        if (coin == null) {
            coin = new Coin(centValue,0,false);
        }
        return coin;
    }
}
