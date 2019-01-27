package com.juanfrasr.interfaces;

import com.juanfrasr.model.Bank;
import com.juanfrasr.model.Coin;
import com.sun.istack.NotNull;


import java.util.List;

public interface CoinsService {
    List<Coin> parseCoins(@NotNull final String coinString);
    List<Coin> addCoin(@NotNull List<Coin> lCoins, String coin);
    List<Coin> returnCoin(@NotNull double price);
    Bank insertCoin(List<Coin> lCoins,@NotNull Bank bank) ;
}
