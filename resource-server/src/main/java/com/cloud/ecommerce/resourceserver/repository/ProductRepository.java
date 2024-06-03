package com.cloud.ecommerce.resourceserver.repository;

import com.cloud.ecommerce.resourceserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
