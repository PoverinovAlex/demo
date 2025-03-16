package com.demo.repositories;

import com.demo.model.Product;

import java.util.List;

public interface ProductInfoRepository {
    Product findByIdProduct(Integer id);
    List<Product> findByName(String name);
    List<Product> findByProteinsBetween(float startPro, float endPro);
    List<Product> findByFatsBetween(float startFat, float endFat);
    List<Product> findByCarbohydratesBetween(float startCar, float endCar);
    List<Product> findByCaloriesBetween(float startCal, float endCal);
}
