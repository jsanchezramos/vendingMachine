package com.juanfrasr.interfaces;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProductsInStock(Map<Product, Integer> mProducts);
    ProductStock findProduct(String nameProcuct);
    ProductStock returnOneProduct(Product product);
    List<ProductStock> getAllProductStock();
    ProductStock refilldProduct(Product product,int quantity);
}
