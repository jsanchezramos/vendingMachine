package com.juanfrasr.model;

import java.util.List;

public class Bank {

    private List<Coin> available;
    private double total;

    public Bank(List<Coin> available) {
        this.available = available;
    }

    public List<Coin> getAvailable() {
        return available;
    }

    public void setAvailable(List<Coin> available) {
        this.available = available;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}