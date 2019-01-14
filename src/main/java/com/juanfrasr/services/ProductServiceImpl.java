package com.juanfrasr.services;

import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.repository.Memory;
import com.juanfrasr.repository.MemoryImpl;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService , ILogger{


    private Memory productMemory = MemoryImpl.getInstance();

    @Override
    public void addProductsInStock(Map<Product, Integer> mProducts) {
        mProducts.entrySet().stream().forEach(m -> productMemory.addProduct(m.getKey(),m.getValue()));
    }

    @Override
    public Product findProduct(String nameProcuct) {
        return null;
    }

    @Override
    public void cancelProduct(Product producr) {

    }

    @Override
    public List<ProductStock> getAllProductStock() {
        return productMemory.getAllProducts();
    }
}
