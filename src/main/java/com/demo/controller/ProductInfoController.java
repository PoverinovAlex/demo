/*
package com.demo.controller;
import java.util.Optional;

import com.demo.model.ProductInfo;
import com.demo.repositories.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ProductInfoController {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    // Создание продукта
    @PostMapping
    public ProductInfo createProductInfo(@RequestBody ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
@GetMapping("/{id}")
    public ResponseEntity<ProductInfo> getProductInfoById(@PathVariable(name = "id") int id) {
        ProductInfo productInfo = productInfoRepository.findById(id);

        if (productInfo.isPresent()) {
            return new ResponseEntity<>(productInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
*/
