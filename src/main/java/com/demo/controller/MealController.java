
package com.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.demo.DTO.MealDTO;
import com.demo.DTO.UserDTO;
import com.demo.model.Meal;
import com.demo.model.User;
import com.demo.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    // Создание приема
    @PostMapping
    public Meal createMeal(@RequestBody Meal meal) {
        return mealService.GetMealRepository().save(meal);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MealDTO> getMealById(@PathVariable(name = "id") int id){
        Meal meal = mealService.GetMealRepository().findById(id);
        MealDTO mealDTO = new MealDTO(meal);

        return mealDTO != null
                ? new ResponseEntity<MealDTO>(mealDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{date}")
    public ResponseEntity<MealDTO> getMealByName(@PathVariable LocalDateTime date) {
        Meal meal = (Meal) mealService.GetMealRepository().findByDate(date); // Предполагаем, что метод возвращает Product или null
        MealDTO mealDTO = new MealDTO(meal);
        if (mealDTO != null) {
            return ResponseEntity.ok().body(mealDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Date/{startDate}/{endDate}")
    public ResponseEntity<List<MealDTO>> getProductsByCalories(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        List<Meal> meals = mealService.GetMealRepository().findByDateBetween(startDate, endDate);
        List<MealDTO> mealDTOList = new ArrayList<>();
        for (Meal meal : meals){
            MealDTO mealDTO = new MealDTO(meal);
            mealDTOList.add(mealDTO);
        }
        if (!mealDTOList.isEmpty()) {
            return ResponseEntity.ok().body(mealDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer id, @RequestBody Meal mealDetails) {
        return mealService.GetMealRepository().findById(id)
                .map(meal -> {
                    meal.SetDate(mealDetails.GetDate());
                    meal.SetName(mealDetails.GetName());
                    Meal updatedMeal = mealService.GetMealRepository().save(meal);
                    return ResponseEntity.ok().body(updatedMeal);
                })
                .orElse(ResponseEntity.notFound().build());
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


}
