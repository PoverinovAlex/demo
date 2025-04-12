package com.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "product information")
public class ProductInfo {

    //@EmbeddedId
/*    private ProductInfoId id;*/

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public ProductInfo(int quantity, Meal meal, Product product) {
        this.quantity = quantity;

        this.meal = meal;
        this.product = product;
    }

    // Геттеры и сеттеры


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }


}
