package com.interview.amex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.amex.models.Order;
import com.interview.amex.models.ProductItem;
import com.interview.amex.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    OrderService orderService;

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

    ProductItem productItem_3 = new ProductItem(
            "Apple",
            new Random().nextInt(),
            0.60,
            3
    );
    ProductItem productItem_4 = new ProductItem(
            "Orange",
            new Random().nextInt(),
            0.25,
            6
    );

    @Test
    void placeNewOrder() throws Exception {

        List<ProductItem> productItems = new ArrayList<>(Arrays.asList(productItem_1, productItem_2));
        Order ORDER_1 = new Order(productItems);

        Mockito.when(orderService.placeNewOrder(ORDER_1)).thenReturn(new ResponseEntity<>(ORDER_1, HttpStatus.OK));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(ORDER_1));

         mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderTotal", is(0.85)));

    }

    @Test
    void placeNewOrder2() throws Exception {

        List<ProductItem> productItems = new ArrayList<>(Arrays.asList(productItem_3, productItem_4));
        Order ORDER_1 = new Order(productItems);

        Mockito.when(orderService.placeNewOrder(ORDER_1)).thenReturn(new ResponseEntity<>(ORDER_1, HttpStatus.OK));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(ORDER_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderTotal", is(2.2)));

    }
}