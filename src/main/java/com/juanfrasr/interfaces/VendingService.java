package com.juanfrasr.interfaces;

import com.juanfrasr.model.Product;

import java.util.Map;

public interface VendingService {
    Boolean startVendingMachine(final String coinString);
    Boolean addNewProductVending(final Map<Product,Integer> product);
    Boolean sellProduct(Product product);
    void addCoinVending(final String coinString);
    double getCoinCurrent();
}
