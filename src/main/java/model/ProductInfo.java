package model;

import jakarta.persistence.*;


@Entity
@Table(name = "product information")
public class ProductInfo {
    @Id
    @Column
    private int productId;
    @Column
    private int mealId;
    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private model.Meal Meal;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private model.Product Product;

    // Конструкторы
    public ProductInfo() {}

    public ProductInfo(int productId, int mealId, int quantity) {
        this.productId = productId;
        this.mealId = mealId;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
