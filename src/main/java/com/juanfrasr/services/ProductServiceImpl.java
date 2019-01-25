package com.juanfrasr.services;

import com.juanfrasr.exceptions.NotProductInStock;
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
    public ProductStock findProduct(String nameProcuct) {
        List<ProductStock> lProduct = productMemory.getAllProducts();
        return lProduct.stream().filter(p-> p.getProduct().getName().equals(nameProcuct)).findFirst().orElse(null);

    }

    @Override
    public ProductStock returnProduct(Product product) {
        ProductStock productStock = findProduct(product.getName());
        log().info("Stock product: "+ productStock.getQuantity());

        productStock.setQuantity(productStock.getQuantity() - 1);

        productMemory.updateProductStock(productStock);

        return  productStock;
    }


    @Override
    public List<ProductStock> getAllProductStock() {
        if(productMemory.getAllProducts().size() < 0){
            throw new NotProductInStock();
        }else{
            return productMemory.getAllProducts();
        }
    }

    @Override
    public ProductStock rechargeProduct(Product product, int quantity) {
        ProductStock productStock = findProduct(product.getName());
        productStock.setQuantity(quantity + productStock.getQuantity());
        productMemory.updateProductStock(productStock);

        return productStock;
    }
}
