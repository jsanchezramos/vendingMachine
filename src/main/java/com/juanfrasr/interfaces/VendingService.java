package com.juanfrasr.interfaces;

import com.juanfrasr.model.Product;

import java.util.Map;

public interface VendingService {
    Boolean upServiceMachine(final String coinString);
    Boolean addNewProductVending(final Map<Product,Integer> product);
}
