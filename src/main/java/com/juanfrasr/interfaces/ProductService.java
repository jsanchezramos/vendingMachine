package com.juanfrasr.interfaces;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProductsInStock(Map<Product, Integer> mProducts);
    ProductStock findProductByName(String nameProcuct);
    ProductStock findProduct(Product product);
    ProductStock returnProductStock(Product product);
    List<ProductStock> getAllProductStock();
    ProductStock addQuantityInProduct(Product product, int quantity);
}
