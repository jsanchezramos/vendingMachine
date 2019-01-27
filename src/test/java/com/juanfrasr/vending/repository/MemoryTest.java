package com.juanfrasr.vending.repository;


import com.juanfrasr.model.Product;
import com.juanfrasr.model.ProductStock;
import com.juanfrasr.repository.Memory;
import com.juanfrasr.repository.MemoryImpl;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Test to valid information in memory
 */
public class MemoryTest {

    private Memory memory = new MemoryImpl();

    /**
     * add product in memory
     */
   @Test
   public void addProductInMemory(){
       assertTrue(memory.getAllProducts().size()==3);
       Product product = new Product("Fanta",2);
       memory.addProduct(product,20);
       assertTrue(memory.getAllProducts().size()==4);
   }
    /**
     * get all productsStock
     */
    @Test
    public void getAllProductsStock(){
        assertNotNull(memory.getAllProducts());
    }
    /**
     * Update product Stock
     */
    @Test
    public void updateProductStock(){
        List<ProductStock> lProductStock = memory.getAllProducts();
        ProductStock productStock = lProductStock.get(0);
        assertTrue(productStock.getQuantity() == 10);
        productStock.setQuantity(30);
        memory.updateProductStock(productStock);
        assertTrue(productStock.getQuantity() == 30);
    }
}
