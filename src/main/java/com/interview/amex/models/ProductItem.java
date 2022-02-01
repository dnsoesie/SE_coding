package com.interview.amex.models;

import java.util.Random;

public class ProductItem {

    private String productName;
    private int productId = new Random().nextInt();
    private double productCost;
    private int count;

    public ProductItem() {
    }

    public ProductItem(String productName, int productId, double productCost, int count) {
        this.productName = productName;
        this.productId = productId;
        this.productCost = productCost;
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
