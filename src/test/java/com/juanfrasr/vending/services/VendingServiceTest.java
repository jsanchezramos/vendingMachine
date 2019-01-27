package com.juanfrasr.vending.services;

import com.juanfrasr.interfaces.CoinsService;
import com.juanfrasr.interfaces.ProductService;
import com.juanfrasr.interfaces.VendingService;
import com.juanfrasr.model.Product;
import com.juanfrasr.repository.Memory;
import com.juanfrasr.repository.MemoryImpl;
import com.juanfrasr.services.CoinsServiceImpl;
import com.juanfrasr.services.ProductServiceImpl;
import com.juanfrasr.services.VendingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test vendingService to manage machine vending
 */
public class VendingServiceTest {

    private VendingService vendingService;

    /**
     * Load all objects to simulate vending machine
     */
    @Before
    public void loadInfo(){
        Memory memory = new MemoryImpl();
        CoinsService coinsService = new CoinsServiceImpl();
        ProductService productService = new ProductServiceImpl(memory);

        vendingService = new VendingServiceImpl(coinsService,productService);
    }

    /**
     * Start vending machine to insert correct coins
     */
    @Test
    public void startVendingMachineToInsertCoin(){
        assertFalse(vendingService.startVendingMachine("0.30€ 7€"));
        assertTrue(vendingService.startVendingMachine("0.50€ 2€ 1€ 0.10€"));
    }

    /**
     * add new product in vending machine
     */
    @Test
    public void addNewProductInVending(){
        final Map<Product,Integer> productStockNew = new HashMap<>();

        productStockNew.put(new Product("Fanta",2.30),10);
        assertTrue(vendingService.addNewProductVending(productStockNew));
    }
    /**
     * Sell product in the vending machine
     */
    @Test
    public void sellProductInVeding(){
        Product product= new Product("Sprite",1.40);
        assertFalse(vendingService.sellProduct(product));
        vendingService.startVendingMachine("1€");
        assertFalse(vendingService.sellProduct(product));
        vendingService.startVendingMachine("2€");
        assertTrue(vendingService.sellProduct(product));
    }
    /**
     * Add coin to possible buy product
     */
    @Test
    public void addMoreCoinToByProduct(){
        Product product= new Product("Sprite",1.40);
        vendingService.startVendingMachine("1€");
        assertFalse(vendingService.sellProduct(product));
        vendingService.addCoinVending("0.20€");
        assertFalse(vendingService.sellProduct(product));
        vendingService.addCoinVending("0.20€");
        assertTrue(vendingService.sellProduct(product));
    }
    /**
     * Get current coin
     */
    @Test
    public void getCurrentCoint(){
        vendingService.startVendingMachine("1€");
        assertTrue(vendingService.getCoinCurrent() == 1);
        vendingService.addCoinVending("0.20€");
        assertTrue(vendingService.getCoinCurrent() == 1.20);
        vendingService.addCoinVending("0.20€");
        assertTrue(vendingService.getCoinCurrent() == 1.40);
    }
}
