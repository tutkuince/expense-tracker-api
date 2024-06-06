package com.cloud.ecommerce.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BasketItem {

    private long productId;
    private String title;
    private int quantity;
    private String imageUrl;
    private BigDecimal unitPrice;
    private String brandName;
    private String categoryName;
}
