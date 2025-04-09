package com.demo.services;

import jakarta.persistence.EntityManager;
import com.demo.model.Meal;
import com.demo.repositories.MealRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveMeal(Meal meal) {
        entityManager.persist(meal);
        entityManager.close();
    }
    @Transactional
    public void updateProduct(Meal meal){
        entityManager.merge(meal);
        entityManager.close();
    }

    @Transactional
    public void deleteProduct(Meal meal){
        // Проверяем, управляется ли сущность EntityManager, и если нет, объединяем её перед удалением
        entityManager.remove(entityManager.contains(meal) ? meal : entityManager.merge(meal));
        entityManager.close();
    }

    public MealRepository GetProductInfoRepository (){
        return mealRepository;
    }
}
