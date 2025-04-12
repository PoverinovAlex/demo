package com.demo.services;

import com.demo.model.Meal;
import com.demo.model.Product;
import com.demo.repositories.ProductInfoRepository;
import jakarta.persistence.EntityManager;
import com.demo.model.ProductInfo;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Transactional
    public ProductInfo saveProductInfo(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }


    @Transactional
    public void updateProductInfo(ProductInfo productInfo){
        try {
            ProductInfo oldProductInfo = productInfoRepository.findById(productInfo.getId());
            oldProductInfo.setQuantity(productInfo.getQuantity());
            if(productInfo.getProduct()!=null){
                oldProductInfo.setProduct(productInfo.getProduct());
            }
            if(productInfo.getMeal()!= null){
                oldProductInfo.setMeal(productInfo.getMeal());
            }
            productInfoRepository.save(oldProductInfo);

        } catch (RuntimeException e) {
            // Логирование ошибки или выполнение альтернативных действий
            System.err.println("Error updating user: " + e.getMessage());
            throw e; // Перевыброс исключения, если необходимо
        }
    }

    @Transactional
    public void deleteProductInfo(ProductInfo productInfo){
        productInfoRepository.delete(productInfo);
    }

    public ProductInfoRepository GetProductInfoRepository (){
        return productInfoRepository;
    }
}
