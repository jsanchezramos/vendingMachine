package com.juanfrasr.model;

/**
 * Class Coin to differents types of coins
 */

public class Coin {

    private String name;
    private double value;
    private boolean valid;

    public Coin(String name, double value, boolean valid) {
        this.name = name;
        this.value = value;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
