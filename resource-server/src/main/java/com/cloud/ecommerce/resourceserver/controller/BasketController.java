package com.cloud.ecommerce.resourceserver.controller;

import com.cloud.ecommerce.resourceserver.model.Basket;
import com.cloud.ecommerce.resourceserver.model.BasketData;
import com.cloud.ecommerce.resourceserver.repository.BasketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketData basketData) {
        Basket basket = new Basket(basketData.getId());
        basket.setItems(basketData.getItems());
        basketRepository.save(basket);
        return ResponseEntity.ok(basket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable String id) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        return optionalBasket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBasketById(@PathVariable String id) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (optionalBasket.isPresent()) {
            basketRepository.delete(optionalBasket.get());
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }
}
