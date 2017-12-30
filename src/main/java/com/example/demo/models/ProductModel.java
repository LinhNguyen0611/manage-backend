package com.example.demo.models;

import com.example.demo.entities.Product;

import javax.validation.constraints.NotNull;

public class ProductModel {
    @NotNull
    private String productName;
    @NotNull
    private Float price;

    public ProductModel() {
    }

    public Product toEntity() {
        Product product = new Product();
        product.setPrice(this.getPrice());
        product.setProductName(this.getProductName());
        return product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
