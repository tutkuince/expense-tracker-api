package com.cloud.ecommerce.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ProductController {

    @GetMapping(value = "/categories")
    public List<String> getCategories() {
        return List.of("Shoes", "Jackets", "Suits");
    }

    @GetMapping(value = "/products")
    public List<String> getProducts() {
        return List.of("Shoe-01", "Jacket-01", "Suit-01");
    }
}
