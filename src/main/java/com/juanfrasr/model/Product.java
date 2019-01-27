package com.juanfrasr.model;

/**
 * Class product
 */
public final class Product {

    final private String name;
    final private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
