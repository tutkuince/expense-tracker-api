package com.cloud.ecommerce.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @GetMapping("/orders")
    public List<String> getOrders() {
        return List.of("Order-1", "Order-2", "Order-3");
    }
}
