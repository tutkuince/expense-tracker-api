package com.cloud.ecommerce.resourceserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash("BASKET")
@Data
@NoArgsConstructor
public class Basket {
    private String id;
    private List<BasketItem> items;

    public Basket(String id) {
        this.id = id;
        items = new ArrayList<>();
    }
}
