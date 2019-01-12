package com.juanfrasr.model;

import java.util.List;

public class Wallet {

    private final List<Coin> available;

    public Wallet(List<Coin> available) {
        this.available = available;
    }

    public List<Coin> getAvailable() {
        return available;
    }


}
