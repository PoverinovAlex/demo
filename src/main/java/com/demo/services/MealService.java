package com.demo.services;

import com.demo.model.Product;
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

    @Transactional
    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }


    @Transactional
    public void updateMeal(Meal meal){
        try {
            Meal oldMeal = mealRepository.findById(meal.GetId());
            if (meal.GetName() != null) {
                oldMeal.SetName(meal.GetName());
            }
            if(meal.getProductInfos()!=null){
                oldMeal.setProductInfos(meal.getProductInfos());
            }
            if(meal.getUser()!=null){
                oldMeal.setUser(meal.getUser());
            }
            oldMeal.SetDate(meal.GetDate());
            mealRepository.save(oldMeal);

        } catch (RuntimeException e) {
            // Логирование ошибки или выполнение альтернативных действий
            System.err.println("Error updating user: " + e.getMessage());
            throw e; // Перевыброс исключения, если необходимо
        }
    }

    @Transactional
    public void deleteMeal(Meal meal){
        mealRepository.delete(meal);
    }

    public MealRepository GetMealRepository (){
        return mealRepository;
    }
}
