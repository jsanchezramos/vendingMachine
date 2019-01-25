package com.juanfrasr.interfaces;

import com.juanfrasr.model.Bank;
import com.juanfrasr.model.Coin;
import com.juanfrasr.model.Product;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface CoinsService {
    List<Coin> parseCoins(@NotNull final String coinString);
    List<Coin> addCoin(List<Coin> lCoins, String coin);
    List<Coin> returnCoin(List<Coin> lCoin, double price);
    Bank insertCoin(List<Coin> lCoins, Bank bank) ;
}
