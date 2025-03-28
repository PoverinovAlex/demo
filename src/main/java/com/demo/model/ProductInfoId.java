package com.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class ProductInfoId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Конструкторы, геттеры и сеттеры
}