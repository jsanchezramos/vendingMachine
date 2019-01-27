package com.juanfrasr.vending;

import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.services.ProductServiceImpl;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Product Test
 */

public class ProductTest {

    /**
     * Return product in memory is 10 products Coke.
     */
    @Test
    public void returnProduct(){
        ProductService productService = new ProductServiceImpl();

        Product product = new Product("Coke",1.50);
        ProductStock productStock = productService.returnProduct(product);

        assertTrue(productStock.getQuantity() == 9);
    }

    /**
     * Refill product
     */
    @Test
    public void refillProduct(){
        ProductService productService = new ProductServiceImpl();
        List<ProductStock> allProductStock =  productService.getAllProductStock();
        Product product = allProductStock.get(0).getProduct();

        ProductStock productStock = productService.rechargeProduct(product,100);

        assertTrue(productStock.getQuantity() > 10);
    }
}
