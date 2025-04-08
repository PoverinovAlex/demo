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
    public void saveProduct(Product product) {
        entityManager.persist(product);
        entityManager.close();
    }
    @Transactional
    public void updateProduct(Product product){
        entityManager.merge(product);
        entityManager.close();
    }

    @Transactional
    public void deleteProduct(Product product){
        // Проверяем, управляется ли сущность EntityManager, и если нет, объединяем её перед удалением
        entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
        entityManager.close();
    }

    public ProductRepository GetProductRepository (){
        return productRepository;
    }
}


 /*   public void saveProduct(Product Product) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(Product);
        transaction.commit();
        entityManager.close();
    }
    public void deleteProduct(Product Product) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(Product) ? Product : entityManager.merge(Product));
        transaction.commit();
        entityManager.close();
    }*/