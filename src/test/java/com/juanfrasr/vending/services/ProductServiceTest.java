package com.juanfrasr.vending.services;

import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.repository.Memory;
import com.juanfrasr.repository.MemoryImpl;
import com.juanfrasr.services.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;

/**
 * Test logical productService
 */
public class ProductServiceTest {

    final private Memory memory = new MemoryImpl();
    private ProductService productService;


    @Before
    public void loadOject(){
        productService = new ProductServiceImpl(memory);
    }

    /**
     * add new product and stock
     */
    @Test
    public void addProductInStock(){
        final Map<Product,Integer> tempProductAdd = new HashMap<>();
        tempProductAdd.put(new Product("Fanta",2.30),10);
        assertTrue(memory.getAllProducts().size() == 3);
        productService.addProductsInStock(tempProductAdd);
        assertTrue(memory.getAllProducts().size() == 4);
    }

    /**
     * Find product to name string and return object productStock
     */
    @Test
    public void findProductByName(){
        ProductStock productStock = productService.findProductByName("Fanta");
        assertNull(productStock);
        productStock = productService.findProductByName("Sprite");
        assertNotNull(productStock);
    }

    /**
     * Find product to object product and return object productStock
     */
    @Test
    public void findProductByObject(){
        Product product = new Product("Fanta",2);
        ProductStock productStock = productService.findProduct(product);
        assertNull(productStock);
        Product productCorrect = new Product("Sprite",1.40);
        productStock = productService.findProduct(productCorrect);
        assertNotNull(productStock);
    }

    /**
     * Return productStock to object product
     */
    @Test
    public void returnProductStock(){
        Product product = new Product("Fanta",2);
        ProductStock productStock = productService.returnProductStock(product);
        assertNull(productStock);
        Product productCorrectName = new Product("Sprite",1.10);
        productStock = productService.returnProductStock(productCorrectName);
        assertNull(productStock);
        Product productCorrect = new Product("Sprite",1.40);
        productStock = productService.returnProductStock(productCorrect);
        assertNotNull(productStock);
    }

    /**
     * Get all products in stock is 3 items in memory
     */
    @Test
    public void getAllProductsStock(){
        List<ProductStock> lProductStock = productService.getAllProductStock();
        assertNotNull(lProductStock);
        assertTrue(lProductStock.size() == 3);
    }
    /**
     * add items in the product
     */
    @Test
    public void addQuantityInTheProduct(){
        Product product= new Product("Sprite",1.40);
        ProductStock productStock = productService.findProduct(product);
        assertTrue(productStock.getQuantity() == 20);
        productService.addQuantityInProduct(product,30);
        productStock = productService.findProduct(product);
        assertTrue(productStock.getQuantity() == 50);
    }
}
