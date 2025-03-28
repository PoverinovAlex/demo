package com.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "product information")
public class ProductInfo {

    @EmbeddedId
    private ProductInfoId id;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "meal_id", insertable = false, updatable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    // Конструкторы
    public ProductInfo() {}

    public ProductInfo(int productId, int quantity) {

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
