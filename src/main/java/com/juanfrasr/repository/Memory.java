package com.juanfrasr.repository;

import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface Memory {
    void addProduct(@NotNull Product product,@NotNull int quantity);
    List<ProductStock> getAllProducts();
    void updateProductStock(@NotNull ProductStock productStock);
}
