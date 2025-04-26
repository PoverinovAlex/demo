package com.demo.controller;
import java.util.Optional;

import com.demo.model.Product;
import com.demo.model.ProductInfo;
import com.demo.repositories.ProductInfoRepository;
import com.demo.services.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productInfos")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    // Создание продукта
    @PostMapping
    public ProductInfo createProductInfo(@RequestBody ProductInfo productInfo) {
        return productInfoService.GetProductInfoRepository().save(productInfo);
    }
@GetMapping("/{id}")
    public ResponseEntity<ProductInfo> getProductInfoById(@PathVariable(name = "id") int id) {
        ProductInfo productInfo = productInfoService.GetProductInfoRepository().findById(id);

        return productInfo != null
            ? new ResponseEntity<>(productInfo, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
