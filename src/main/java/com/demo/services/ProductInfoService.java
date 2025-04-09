package com.demo.services;

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
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveProductInfo(ProductInfo productInfo) {
        entityManager.persist(productInfo);
        entityManager.close();
    }
    @Transactional
    public void updateProductInfo(Product productInfo){
        entityManager.merge(productInfo);
        entityManager.close();
    }

    @Transactional
    public void deleteProductInfo(Product productInfo){
        // Проверяем, управляется ли сущность EntityManager, и если нет, объединяем её перед удалением
        entityManager.remove(entityManager.contains(productInfo) ? productInfo : entityManager.merge(productInfo));
        entityManager.close();
    }

    public ProductInfoRepository GetProductInfoRepository (){
        return productInfoRepository;
    }
}
