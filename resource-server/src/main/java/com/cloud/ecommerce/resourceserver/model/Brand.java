package com.cloud.ecommerce.resourceserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;
    private String brandName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    private Set<Product> products;

    public Brand(Long brandId) {
        this.brandId = brandId;
    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }
}
