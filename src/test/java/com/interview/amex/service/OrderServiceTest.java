package com.interview.amex.service;

import com.interview.amex.models.Order;
import com.interview.amex.models.ProductItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderService.class)
public class OrderServiceTest {

    @Autowired
    MockMvc mockMvc;

    ProductItem productItem_1 = new ProductItem(
            "Apple",
            new Random().nextInt(),
            0.60,
            1
    );
    ProductItem productItem_2 = new ProductItem(
            "Orange",
            new Random().nextInt(),
            0.25,
            1
    );

    @Test
    void placeNewOrder() {
        List<ProductItem> productItems = new ArrayList<>(Arrays.asList(productItem_1, productItem_2));
        Order ORDER_1 = new Order(productItems);
    }
}