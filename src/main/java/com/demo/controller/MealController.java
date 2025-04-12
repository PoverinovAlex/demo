/*
package com.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.demo.model.Meal;
import com.demo.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class MealController {

    @Autowired
    private MealService mealService;

    // Создание приема
    @PostMapping
    public Meal createMeal(@RequestBody Meal meal) {
        return mealService.GetMealRepository().save(meal);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable(name = "id") int id){
        Meal meal = mealService.GetMealRepository().findById(id);
        return meal != null
                ? new ResponseEntity<>(meal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{date}")
    public ResponseEntity<Meal> getMealByName(@PathVariable Date date) {
        Meal meal = (Meal) mealService.GetMealRepository().findByDate(date); // Предполагаем, что метод возвращает Product или null
        if (meal != null) {
            return ResponseEntity.ok().body(meal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Date/{startDate}/{endDate}")
    public ResponseEntity<List<Meal>> getProductsByCalories(@PathVariable Date startDate, @PathVariable Date endDate) {
        List<Meal> meals = mealService.GetMealRepository().findByDateBetween(startDate, endDate);
        if (!meals.isEmpty()) {
            return ResponseEntity.ok().body(meals);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //удаление приема
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Integer id) {

        Optional<Meal> mealOptional = mealService.GetMealRepository().findById(id);
        Meal defaultMeal = new Meal(); // Создайте объект по умолчанию
        Meal meal = mealOptional.orElse(defaultMeal);

        if (meal != null) {
            mealService.GetMealRepository().delete(meal);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}*/
