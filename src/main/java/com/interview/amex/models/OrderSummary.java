package com.interview.amex.models;

public class OrderSummary {

    private Order order;

    public OrderSummary() {
    }

    public OrderSummary(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
