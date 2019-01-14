package com.juanfrasr.vending;

import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.interfaces.VendingService;
import com.juanfrasr.model.Product;
import com.juanfrasr.services.CoinsServiceImpl;
import com.juanfrasr.services.ProductServiceImpl;
import com.juanfrasr.services.VendingServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test to create Vending Machine
 */

public class VendingMachineTest {

    /**
     * Valid to correct create Machine Vending
     */
    @Test
    public void createMachineVending(){

        CoinsService coinsService = new CoinsServiceImpl();
        ProductService productService = new ProductServiceImpl();
        VendingService vendingService = new VendingServiceImpl(coinsService,productService);

        assertTrue(vendingService.upServiceMachine("0.11€ 0.25€ 0.25€ 0.10€ 0.05€"));

    }

    /**
     * No valid money to vending start
     */
    @Test
    public void notValidMoneyToRunVending(){

        CoinsService coinsService = new CoinsServiceImpl();
        ProductService productService = new ProductServiceImpl();
        VendingService vendingService = new VendingServiceImpl(coinsService,productService);

        assertFalse(vendingService.upServiceMachine("0.12€ 0.25€ 0.25€ 0.69€ 0.035€"));
    }

    /**
     * Add new product and stock
     */
    @Test
    public void vendingAddNewProductStock(){

        final Map<Product,Integer> PRODUTS_STOCK = new HashMap<>();

        PRODUTS_STOCK.put(new Product("Fanta",2.30),10);

        CoinsService coinsService = new CoinsServiceImpl();
        ProductService productService = new ProductServiceImpl();
        VendingService vendingService = new VendingServiceImpl(coinsService,productService);

        assertTrue(vendingService.addNewProductVending(PRODUTS_STOCK));

        assertTrue(vendingService.upServiceMachine("0.11€ 0.25€ 0.25€ 0.10€ 0.05€"));

    }

}
