package com.juanfrasr.repository;

import com.juanfrasr.model.ProductStock;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface Memory {
    List<ProductStock> getMemory();
    void addMemoryProduct(@NotNull ProductStock productStock);
}
