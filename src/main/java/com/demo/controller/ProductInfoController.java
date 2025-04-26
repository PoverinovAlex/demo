package com.demo.controller;
import java.util.Optional;

import com.demo.DTO.ProductInfoDTO;
import com.demo.model.Meal;
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
    public ResponseEntity<ProductInfoDTO> getProductInfoById(@PathVariable(name = "id") int id) {
        ProductInfo productInfo = productInfoService.GetProductInfoRepository().findById(id);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO(productInfo);

        return productInfoDTO != null
            ? new ResponseEntity<>(productInfoDTO, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductInfo> updateProductInfo(@PathVariable Integer id, @RequestBody ProductInfo productInfoDetails) {
        return productInfoService.GetProductInfoRepository().findById(id)
                .map(productInfo -> {
                    productInfoDetails.setQuantity(productInfoDetails.getQuantity());
                    ProductInfo updatedProductInfo = productInfoService.GetProductInfoRepository().save(productInfo);
                    return ResponseEntity.ok().body(updatedProductInfo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return productInfoService.GetProductInfoRepository().findById(id)
                .map(productInfo -> {
                    productInfoService.deleteProductInfo(productInfo);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
