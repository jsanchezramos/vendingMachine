package com.juanfrasr.repository;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.services.ILogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MemoryImpl implements Memory, ILogger {

    private static MemoryImpl instance;

    public static synchronized MemoryImpl getInstance(){
        if(instance == null){
            instance = new MemoryImpl();
        }
        return instance;
    }

    private List<ProductStock> lProductsStock = Arrays.asList(
            new ProductStock(new Product("Coke",1.50), 10),
            new ProductStock(new Product("Sprite",1.40), 20),
            new ProductStock(new Product("Water",0.90), 2));

    @Override
    public void addProduct(Product product,int quantity) {
        List<ProductStock> productStocks = new ArrayList<ProductStock>(this.lProductsStock);
        productStocks.add(new ProductStock(product,quantity));
    }

    @Override
    public List<ProductStock> getAllProducts(){
        return lProductsStock;
    }

    @Override
    public void updateProductStock(ProductStock productStock) {
        OptionalInt indexOpt = IntStream.range(0, lProductsStock.size())
                .filter(i -> productStock.equals(lProductsStock.get(i)))
                .findFirst();

        lProductsStock.set(indexOpt.getAsInt(),productStock);
    }

}
