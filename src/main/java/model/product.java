package model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class product {

    @Id
    private int id;
    private String name;
    private float proteins;
    private float fats;
    private float carbohydrates;
    private float calories;

    // Конструкторы, геттеры и сеттеры
}
