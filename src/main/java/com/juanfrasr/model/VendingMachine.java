package com.juanfrasr.model;

import java.util.List;

public class VendingMachine {

    private final List<ProductStock> lStockProduct;
    private final Wallet waller;

    public VendingMachine(List<ProductStock> lStockProduct, Wallet waller) {
        this.lStockProduct = lStockProduct;
        this.waller = waller;
    }
}
