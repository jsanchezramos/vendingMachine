package com.juanfrasr.services;

import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.repository.Memory;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService , ILogger{

    private Memory productMemory;

    public ProductServiceImpl(Memory productMemory) {
        this.productMemory = productMemory;
    }

    @Override
    public void addProductsInStock(Map<Product, Integer> mProducts) {
        mProducts.entrySet().stream().forEach(m -> productMemory.addProduct(m.getKey(),m.getValue()));

    }

    @Override
    public ProductStock findProductByName(String nameProcuct) {
        List<ProductStock> lProduct = productMemory.getAllProducts();
        return lProduct.stream().filter(p-> p.getProduct().getName().equals(nameProcuct)).findFirst().orElse(null);

    }

    @Override
    public ProductStock findProduct(Product product) {
        List<ProductStock> lProduct = productMemory.getAllProducts();
        return lProduct.stream().filter(p-> p.getProduct().getPrice() == product.getPrice() && p.getProduct().getName().equals(product.getName())).findFirst().orElse(null);
    }

    @Override
    public ProductStock returnProductStock(Product product) {
        ProductStock productStock = null;
        productStock = findProduct(product);

        if(productStock != null){
            log().info("Stock product: "+ productStock.getQuantity());
            productStock.setQuantity(productStock.getQuantity() - 1);
            productMemory.updateProductStock(productStock);
        }

        return  productStock;
    }


    @Override
    public List<ProductStock> getAllProductStock() {
        return productMemory.getAllProducts();
    }

    @Override
    public ProductStock addQuantityInProduct(Product product, int quantity) {
        ProductStock productStock = findProductByName(product.getName());
        productStock.setQuantity(quantity + productStock.getQuantity());
        productMemory.updateProductStock(productStock);

        return productStock;
    }
}
