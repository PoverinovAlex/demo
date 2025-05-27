
package com.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.DTO.MealDTO;
import com.demo.DTO.UserDTO;
import com.demo.model.Meal;
import com.demo.model.User;
import com.demo.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping
    public String createMeal(@ModelAttribute MealDTO mealDTO) {
        System.out.println(mealDTO);
        // Логика сохранения приема пищи
        return "redirect:/meals/";
    }

    @GetMapping("/{id}")
    public String getMealById(@PathVariable(name = "id") int id, Model model) {
        Meal meal = mealService.GetMealRepository().findById(id);
        if (meal != null) {
            model.addAttribute("meal", new MealDTO(meal));
            return "meal-detail"; // Представление meal-detail.html
        } else {
            return "not-found"; // Представление not-found.html
        }
    }

    @GetMapping("/get_by_user/{userID}")
    public String getMealByUserID(@PathVariable int userID, Model model) {
        List<Meal> meals = mealService.GetMealRepository().findByUserId(userID);
        List<MealDTO> mealDTOList = meals.stream()
                .map(MealDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("meals", mealDTOList);
        return "meals-list"; // Представление meals-list.html
    }

/*    @GetMapping("/{date}")
    public String getMealByName(@PathVariable LocalDateTime date, Model model) {
        Meal meal = mealService.GetMealRepository().findByDate(date);
        if (meal != null) {
            model.addAttribute("meal", new MealDTO(meal));
            return "meal-detail"; // Представление meal-detail.html
        } else {
            return "not-found"; // Представление not-found.html
        }
    }*/

    @GetMapping("/Date/{startDate}/{endDate}")
    public String getProductsByCalories(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate, Model model) {
        List<Meal> meals = mealService.GetMealRepository().findByDateBetween(startDate, endDate);
        List<MealDTO> mealDTOList = meals.stream()
                .map(MealDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("meals", mealDTOList);
        return "meals-list"; // Представление meals-list.html
    }

    @PostMapping("/{id}")
    public String updateMeal(@PathVariable Integer id, @ModelAttribute Meal mealDetails) {
        mealService.GetMealRepository().findById(id)
                .ifPresent(meal -> {
                    meal.SetDate(mealDetails.GetDate());
                    meal.SetName(mealDetails.GetName());
                    mealService.GetMealRepository().save(meal);
                });
        return "redirect:/meals/";
    }

    @PostMapping("/{id}/delete")
    public String deleteMeal(@PathVariable Integer id) {
        mealService.GetMealRepository().findById(id)
                .ifPresent(meal -> mealService.GetMealRepository().delete(meal));
        return "redirect:/meals/";
    }
}
