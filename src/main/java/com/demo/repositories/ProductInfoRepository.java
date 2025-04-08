package com.demo.repositories;

import com.demo.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    ProductInfo findById(int id);
    List<ProductInfo>  findByMealId(Integer id);
}
