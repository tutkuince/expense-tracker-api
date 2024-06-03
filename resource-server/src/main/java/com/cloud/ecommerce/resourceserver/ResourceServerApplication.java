package com.cloud.ecommerce.resourceserver;

import com.cloud.ecommerce.resourceserver.model.Brand;
import com.cloud.ecommerce.resourceserver.model.Category;
import com.cloud.ecommerce.resourceserver.model.Product;
import com.cloud.ecommerce.resourceserver.repository.BrandRepository;
import com.cloud.ecommerce.resourceserver.repository.CategoryRepository;
import com.cloud.ecommerce.resourceserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            List<Brand> brands = List.of(new Brand("Adidas"), new Brand("Nike"), new Brand("New Balance"));
            brandRepository.saveAll(brands);

            List<Category> categories = List.of(new Category("Running"), new Category("Tennis"), new Category("Basketball"));
            categoryRepository.saveAll(categories);

            List<Product> products = List.of(
                    new Product("AD101", "World Star", "Shoes for next century", new BigDecimal("195.5"), "shoe-1.jpg", true, 12, LocalDateTime.now(), LocalDateTime.now(), new Category(2L), new Brand(1L)),
                    new Product("NI101", "White Line", "will make you world champ", new BigDecimal("293.5"), "shoe-2.jpg", true, 12, LocalDateTime.now(), LocalDateTime.now(), new Category(3L), new Brand(2L)),
                    new Product("NE101", "530", "Run Forrest Run", new BigDecimal("95.5"), "shoe-3.jpg", true, 12, LocalDateTime.now(), LocalDateTime.now(), new Category(1L), new Brand(3L))
            );
            productRepository.saveAll(products);
        };
    }
}
