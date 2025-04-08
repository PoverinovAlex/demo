package com.demo;

import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repositories.ProductRepository;
import com.demo.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;

@SpringBootTest
public class DemoApplicationTest2 {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads(){
        Product tomato = new Product(1, "tomato", 0.6f, 0.2f, 4.4f, 22);
        productService.saveProduct(tomato);
    }

    @Test
    void contextLoads2(){
        Product tomato = productService.GetProductRepository().findById(1);
        tomato.setFats(0.23f);
        productService.updateProduct(tomato);
    }

    @Test
    void contextLoads3(){
        Product cheese = new Product(2, "cheese", 23.2f, 29.5f, 0, 364);
        Product chicken = new Product(3, "chicken breast", 26.91f, 2.77f, 0.59f, 134.02f);
        Product bread = new Product(4, "wheat bread", 7.7f, 2.4f, 53.4f, 254);

        productService.saveProduct(cheese);
        productService.saveProduct(chicken);
        productService.saveProduct(bread);

        List<Product> products = productService.GetProductRepository().findByCaloriesBetween(100, 300);

        for (Product product : products) {
            product.setCalories(product.getCalories() + 2.5f);
            productService.updateProduct(product);
        }
    }
    @Test
    void contextLoads4(){
        Product chicken2 = new Product(31, "chicken drumstick", 21.53f, 8.68f, 0.14f, 160);
        Product chicken3 = new Product(32, "chicken thigh", 19.74f, 10.62f, 0, 167.1f);

        productService.saveProduct(chicken2);
        productService.saveProduct(chicken3);


        List<Product> products = productService.GetProductRepository().findByNameLike("chicken %");
        for (Product product : products) {
            String name = product.getName();
            StringBuilder stringBuilder = new StringBuilder(name);
            stringBuilder.setCharAt(0, 'С');
            product.setName(stringBuilder.toString());
            productService.updateProduct(product);
        }

    }

}
