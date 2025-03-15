package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import model.product;

public class ProductService {
    private EntityManagerFactory emf;
    public void saveProduct(product Product) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(Product);
        transaction.commit();
        entityManager.close();
    }
    public void deleteProduct(product Product) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(Product) ? Product : entityManager.merge(Product));
        transaction.commit();
        entityManager.close();
    }
}
