/*
package com.demo.controller;

import com.demo.model.Product;
import com.demo.repositories.ProductRepository;
import com.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Создание продукта
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    // Получение продукта по ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") int id){
        Product product = productService.GetProductRepository().findById(id);
        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/proteins/{startPro}/{endPro}")
    public ResponseEntity<List<Product>> getProductsByProteins(@PathVariable float startPro, @PathVariable float endPro) {
        List<Product> products = productService.GetProductRepository().findByProteinsBetween(startPro, endPro);
        if (!products.isEmpty()) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fats/{startFat}/{endFat}")
    public ResponseEntity<List<Product>> getProductsByFats(@PathVariable float startFat, @PathVariable float endFat) {
        List<Product> products = productService.GetProductRepository().findByFatsBetween(startFat, endFat);
        if (!products.isEmpty()) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carbohydrates/{startCar}/{endCar}")
    public ResponseEntity<List<Product>> getProductsByCarbohydrates(@PathVariable float startCar, @PathVariable float endCar) {
        List<Product> products = productService.GetProductRepository().findByCarbohydratesBetween(startCar, endCar);
        if (!products.isEmpty()) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/calories/{startCal}/{endCal}")
    public ResponseEntity<List<Product>> getProductsByCalories(@PathVariable float startCal, @PathVariable float endCal) {
        List<Product> products = productService.GetProductRepository().findByCaloriesBetween(startCal, endCal);
        if (!products.isEmpty()) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Получение всех продуктов
    @GetMapping
    public List<Product> getAllProduct() {
        return productService.GetProductRepository().findAll();
    }

    // Обновление продукта
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        Optional<Product> productOptional = productService.GetProductRepository().findById(id);

        Product defaultProduct = new Product(); // Создайте объект по умолчанию
        Product product = productOptional.orElse(defaultProduct);

        if (product != null) {
            product.setName(productDetails.getName());
            product.setProteins(productDetails.getProteins());
            product.setFats(productDetails.getFats());
            product.setCarbohydrates(productDetails.getCarbohydrates());
            product.setCalories(productDetails.getCalories());

            Product updatedProduct = productService.GetProductRepository().save(product);
            return ResponseEntity.ok().body(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {

        Optional<Product> productOptional = productService.GetProductRepository().findById(id);
        Product defaultProduct = new Product(); // Создайте объект по умолчанию
        Product product = productOptional.orElse(defaultProduct);

        if (product != null) {
            productService.GetProductRepository().delete(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}*/
