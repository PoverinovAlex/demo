package com.demo.controller;

import com.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // Создание продукта
    @PostMapping
    public Product createUser(@RequestBody Product product) {
        return productRepository.save(product);
    }
    // Получение продукта по ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }
    // Получение продукта по названию
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        return productRepository.findByName(name)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{proteins}")
    public ResponseEntity<Product> getProductByProteins(@PathVariable float startPro, float endPro) {
        return productRepository.findByProteinsBetween(startPro,endPro)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{fats}")
    public ResponseEntity<Product> getProductByFats(@PathVariable float startFat, float endFat) {
        return productRepository.findByFatsBetween(startFat,endFat)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{carbohydrates}")
    public ResponseEntity<Product> getProductByCarbohydrates(@PathVariable float startCar, float endCar) {
        return productRepository.findByCarbohydratesBetween(startCar,endCar)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{calories}")
    public ResponseEntity<Product> getProductByCarbohydrates(@PathVariable float startCal, float endCal) {
        return productRepository.findByCaloriesBetween(startCal, endCal)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }
    // Получение всех продуктов
    @GetMapping
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Обновление пользователя
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setProteins(productDetails.getProteins());
                    product.setFats(productDetails.getFats());
                    product.setCarbohydrates(productDetails.getCarbohydrates());
                    product.setCalories(productDetails.getCalories());
                    Product updatedProduct = productRepository.save(product);
                    return ResponseEntity.ok().body(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}