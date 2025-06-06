package com.demo.repositories;

import com.demo.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findByUserId(int userId);
    List<Meal> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Meal> findByDate(LocalDateTime date);

    // name LIKE 'value%'
    List<Meal> findByNameStartingWith(String prefix);
    // name LIKE '%value'
    List<Meal> findByNameEndingWith(String suffix);

    Meal findById(int id);
}
