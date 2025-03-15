package repositories;

import model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findByUserId(int userId);
    List<Meal> findByDateBetween(Date startDate, Date endDate);
    List<Meal> findByDate(Date date);

    // name LIKE 'value%'
    List<Meal> findByNameStartingWith(String prefix);
    // name LIKE '%value'
    List<Meal> findByNameEndingWith(String suffix);

    Meal findById(int id);
}
