package com.juanfrasr.interfaces;

import com.juanfrasr.model.Coin;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface CoinsService {
    List<Coin> parseCoins(@NotNull final String coinString);
}
