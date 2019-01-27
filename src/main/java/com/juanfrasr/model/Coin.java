package com.juanfrasr.model;

/**
 * Class Coin to differents types of coins
 */

public final class Coin {

    final private String name;
    final private double value;
    final private boolean valid;

    public Coin(String name, double value, boolean valid) {
        this.name = name;
        this.value = value;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public boolean isValid() {
        return valid;
    }
}
