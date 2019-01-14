package com.juanfrasr.model;

import java.util.List;

public class VendingMachine {

    private final List<ProductStock> lStockProduct;
    private final Bank wallet;

    public VendingMachine(List<ProductStock> lStockProduct, Bank wallet) {
        this.lStockProduct = lStockProduct;
        this.wallet = wallet;
    }
}
