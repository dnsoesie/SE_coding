package com.interview.amex.repository;

import com.interview.amex.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersDto extends MongoRepository<Order, String> {

}
