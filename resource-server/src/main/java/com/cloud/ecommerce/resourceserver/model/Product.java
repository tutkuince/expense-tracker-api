package com.cloud.ecommerce.resourceserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;
    private String sku;
    private String title;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
    private boolean isActive;
    private int unitsInStock;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;

    public Product(
            String sku,
            String title,
            String description,
            BigDecimal unitPrice,
            String imageUrl,
            boolean isActive,
            int unitsInStock,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Category category,
            Brand brand) {
        this.sku = sku;
        this.title = title;
        this.description = description;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.unitsInStock = unitsInStock;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.category = category;
        this.brand = brand;
    }
}
