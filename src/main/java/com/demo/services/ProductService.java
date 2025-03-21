package com.demo.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import com.demo.model.Product;
import com.demo.repositories.ProductRepository;

public class ProductService {
    private EntityManagerFactory emf;
    private ProductRepository productRepository;

    public void saveProduct(Product Product) {
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
    }
}
