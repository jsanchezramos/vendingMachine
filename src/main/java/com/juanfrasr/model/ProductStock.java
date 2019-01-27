package com.juanfrasr.model;

/**
 * Class ProductStock to quantity stock this product.
 */

public class ProductStock {

    final private Product product;
    private int quantity;

    public ProductStock(Product product, int quantity) {
        this.product = new Product(product.getName(),product.getPrice());
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
