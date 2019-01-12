package com.juanfrasr.interfaces;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProductsInStock(Map<Product, Integer> mProducts);
    Product findProduct(String nameProcuct);
    void cancelProduct(Product producr);
    List<ProductStock> getAllProductStock();
}
