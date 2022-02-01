package com.interview.amex.service;

import com.interview.amex.models.Order;
import com.interview.amex.models.OrderSummary;
import com.interview.amex.models.ProductItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class OrderService {


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
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
