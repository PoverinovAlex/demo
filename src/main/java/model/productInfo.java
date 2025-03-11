package model;

import jakarta.persistence.*;

@Entity
@Table(name = "product information")
public class productInfo {
    @Id
    private int productId;
    private int mealId;
    private int quantity;

    // Конструкторы, геттеры и сеттеры

}
