package com.interview.amex.controller;

import com.interview.amex.models.Order;
import com.interview.amex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Order> placeNewOrder(@RequestBody Order order) {
        return orderService.placeNewOrder(order);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") String id) {
        return orderService.getOrderById(id);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAllOrdersInDb();
    }
}
