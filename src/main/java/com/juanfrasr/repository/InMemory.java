package com.juanfrasr.repository;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemory implements Memory {

    private static InMemory instance;

    public static synchronized InMemory getInstance(){
        if(instance == null){
            instance = new InMemory();
        }
        return instance;
    }

    private List<ProductStock> lProductsStock = Arrays.asList(
            new ProductStock(new Product("Coke",1.50), 10),
            new ProductStock(new Product("Sprite",1.40), 20),
            new ProductStock(new Product("Water",0.90), 2));

    public List<ProductStock> getMemory(){
        return lProductsStock;
    }

    @Override
    public void addMemoryProduct(ProductStock productStock) {
        List<ProductStock> productStocks = new ArrayList<ProductStock>(this.lProductsStock);
        productStocks.add(productStock);
    }


}
