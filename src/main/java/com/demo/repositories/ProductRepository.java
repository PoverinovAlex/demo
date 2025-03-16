package com.demo.repositories;

import com.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByIdProduct(Integer id);
    List<Product>  findByMealId(Integer id);
}