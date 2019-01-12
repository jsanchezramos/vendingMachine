package com.juanfrasr.services;

import com.juanfrasr.helpers.ProductHelper;
import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService , ILogger{


    private ProductHelper productHelper = ProductHelper.getInstance();

    @Override
    public void addProductsInStock(Map<Product, Integer> mProducts) {
        mProducts.entrySet().stream().forEach(m -> productHelper.addProduct(m.getKey(),m.getValue()));
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
        return productHelper.getAllProducts();
    }
}
