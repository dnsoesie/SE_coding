package com.interview.amex.service;

import com.interview.amex.models.Order;
import com.interview.amex.models.ProductItem;
import com.interview.amex.repository.OrdersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class OrderService {

    private final OrdersDto ordersDto;

    @Autowired
    public OrderService(OrdersDto ordersDto) {
        this.ordersDto = ordersDto;
    }


    public ResponseEntity<Order> placeNewOrder(Order order) {
        List<ProductItem> productItems = order.getProductItemList();

        for (ProductItem item :
                productItems) {
            if (item.getProductName().equals("Apple")) {
                int count = item.getCount();
                double totalCost = order.getOrderTotal();
                double discount;
                if(count % 2 == 0) {
                    discount = (count * item.getProductCost()) / 2;
                } else {
                    // We do 50% discount for each pair of apples, then full price for the unpaired apple
                    discount = (((count - 1) * item.getProductCost()) / 2) + item.getProductCost();
                }
                totalCost = totalCost - discount;
                order.setOrderTotal(totalCost);
            }
            if(item.getProductName().equals("Orange")) {
                int count = item.getCount();
                double totalCost = order.getOrderTotal();
                double discount;
                int remainder = count % 3;
                if(remainder == 0) {
                    int multiplier = count / 3;
                    discount = item.getProductCost() * multiplier;
                } else {
                    discount = (((count - remainder) * item.getProductCost()) / 2) + (remainder * item.getProductCost());
                }
                totalCost = totalCost - discount;
                order.setOrderTotal(totalCost);
            }
        }
        ordersDto.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<Order> getOrderById(String id) {
        Optional<Order> order = ordersDto.findById(id);
        Order order1 = null;
        if(order.isPresent()) {
            order1 = order.get();
        }
        return new ResponseEntity<>(order1, HttpStatus.OK);
    }

    public ResponseEntity<List<Order>> getAllOrdersInDb() {

        List<Order> orders = ordersDto.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
