package com.cloud.ecommerce.resourceserver.model;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BasketData {
    @Nonnull
    private String id;
    private List<BasketItem> items;

}
