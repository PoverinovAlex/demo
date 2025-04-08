package com.demo.repositories;

import com.demo.model.Product;
import com.demo.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);
    Product findByName(String name);
    List <Product> findByNameLike(String str);
    List<Product> findByProteinsBetween(float startPro, float endPro);
    List<Product> findByFatsBetween(float startFat, float endFat);
    List<Product> findByCarbohydratesBetween(float startCar, float endCar);
    List<Product> findByCaloriesBetween(float startCal, float endCal);
}
