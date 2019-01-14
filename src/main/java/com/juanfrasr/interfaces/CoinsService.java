package com.juanfrasr.interfaces;

import com.juanfrasr.model.Bank;
import com.juanfrasr.model.Coin;
import com.juanfrasr.model.Product;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface CoinsService {
    List<Coin> parseCoins(@NotNull final String coinString);
    List<Coin> addCoinToList(List<Coin> lCoins, String coin);
    List<Coin> refundCoin(List<Coin> lCoin, Product product);
    Bank refillCoin(List<Coin> lCoins, Bank bank) ;
}
