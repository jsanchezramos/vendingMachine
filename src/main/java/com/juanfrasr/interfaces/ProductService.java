package com.juanfrasr.interfaces;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProductsInStock(@NotNull Map<Product, Integer> mProducts);
    ProductStock findProductByName(String nameProcuct);
    ProductStock findProduct(@NotNull Product product);
    ProductStock returnProductStock(@NotNull Product product);
    List<ProductStock> getAllProductStock();
    ProductStock addQuantityInProduct(@NotNull Product product,int quantity);
}
