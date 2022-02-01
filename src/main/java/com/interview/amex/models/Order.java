package com.interview.amex.models;

import java.util.List;
import java.util.Random;

public class Order {

    private int orderId;
    private List<ProductItem> productItemList;
    private double orderTotal;

    public Order() {
    }

    /*
    * Creates a new order by receiving a list of product items.
    * A new order id is generated per order.
    * The total cost is calculated based on the count and price of each item
    * in the list.
    * */
    public Order(List<ProductItem> productItemList) {
        double total = 0;
        this.orderId = new Random().nextInt(); // Generate a random Id for the Order.
        this.productItemList = productItemList;

        for (ProductItem productItem :
                productItemList) {
            total = total + (productItem.getCount() * productItem.getProductCost());
        }
        this.orderTotal = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ProductItem> getProductItemList() {
        return productItemList;
    }

    public void setProductItemList(List<ProductItem> productItemList) {
        this.productItemList = productItemList;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
