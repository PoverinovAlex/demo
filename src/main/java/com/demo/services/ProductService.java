package com.demo.services;

import jakarta.persistence.EntityManager;
import com.demo.model.Product;
import com.demo.repositories.ProductRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    public void updateProduct(Product product){
        try {
            Product oldProduct = productRepository.findById(product.getId());
            if (product.getName() != null) {
                oldProduct.setName(product.getName());
            }
            if(product.getProductInfos()!= null){
                oldProduct.setProductInfos(product.getProductInfos());
            }
            oldProduct.setProteins(product.getProteins());
            oldProduct.setFats(product.getFats());
            oldProduct.setCarbohydrates(product.getCarbohydrates());
            oldProduct.setCalories(product.getCalories());
            productRepository.save(oldProduct);
        } catch (RuntimeException e) {
            // Логирование ошибки или выполнение альтернативных действий
            System.err.println("Error updating user: " + e.getMessage());
            throw e; // Перевыброс исключения, если необходимо
        }
    }

    @Transactional
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public ProductRepository GetProductRepository (){
        return productRepository;
    }
}