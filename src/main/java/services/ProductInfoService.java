package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import model.ProductInfo;

public class ProductInfoService {
    private EntityManagerFactory emf;
    public void saveProductInfo(ProductInfo ProductInfo) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(ProductInfo);
        transaction.commit();
        entityManager.close();
    }
    public void deleteProductInfo(ProductInfo ProductInfo) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(ProductInfo) ? ProductInfo : entityManager.merge(ProductInfo));
        transaction.commit();
        entityManager.close();
    }
}
