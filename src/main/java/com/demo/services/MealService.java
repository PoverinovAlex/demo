package com.demo.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import com.demo.model.Meal;
import com.demo.repositories.MealRepository;

public class MealService {
    private EntityManagerFactory emf;
    private MealRepository mealRepository;
    public void saveMeal(Meal Meal) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(Meal);
        transaction.commit();
        entityManager.close();
    }
    public void deleteMeal(Meal Meal) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(Meal) ? Meal : entityManager.merge(Meal));
        transaction.commit();
        entityManager.close();
    }
}
