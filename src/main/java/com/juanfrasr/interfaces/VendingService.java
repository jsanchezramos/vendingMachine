package com.juanfrasr.interfaces;

import com.juanfrasr.model.ProductStock;

import java.util.List;

public interface VendingService {
    Boolean upServiceMachine(final String coinString, final List<ProductStock> lStockProduct);
}
