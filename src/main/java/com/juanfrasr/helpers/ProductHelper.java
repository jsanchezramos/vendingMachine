package com.juanfrasr.helpers;


import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.repository.MemoryImpl;
import com.juanfrasr.repository.Memory;

import java.util.List;

public class ProductHelper {

    private static ProductHelper instance;
    private Memory memory = MemoryImpl.getInstance();
    private List<ProductStock> lProductsStock = memory.getMemory();

    public static synchronized ProductHelper getInstance(){
        if(instance == null){
            instance = new ProductHelper();
        }
        return instance;
    }

    public void addProduct(Product product,int quantity){
        memory.addMemoryProduct(new ProductStock(product,quantity));
    }
    public List<ProductStock> getAllProducts(){
        return memory.getMemory();
    }

}
