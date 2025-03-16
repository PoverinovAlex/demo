package com.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "product information")
public class ProductInfo {

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private com.demo.model.Meal Meal;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private com.demo.model.Product Product;

    // Конструкторы
    public ProductInfo() {}

    public ProductInfo(int productId, int mealId, int quantity) {

        this.quantity = quantity;
    }

    // Геттеры и сеттеры


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
