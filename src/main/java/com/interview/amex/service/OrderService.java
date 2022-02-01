package com.interview.amex.service;

import com.interview.amex.models.Order;
import com.interview.amex.models.OrderSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class OrderService {


    public ResponseEntity<Order> placeNewOrder(Order order) {
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
