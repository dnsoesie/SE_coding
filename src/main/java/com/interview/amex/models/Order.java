package com.interview.amex.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Objects;

@EntityScan
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    @Field("items")
    private List<ProductItem> productItemList;
    @Field("total")
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
        this.productItemList = productItemList;

        for (ProductItem productItem :
                productItemList) {
            total = total + (productItem.getCount() * productItem.getProductCost());
        }
        this.orderTotal = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Double.compare(order.getOrderTotal(), getOrderTotal()) == 0 && getProductItemList().equals(order.getProductItemList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductItemList(), getOrderTotal());
    }
}
