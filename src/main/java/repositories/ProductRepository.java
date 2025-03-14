package repositories;

import model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByIdProduct(Integer id);
    List<Product>  findByMealId(Integer id);
}