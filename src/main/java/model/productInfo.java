package model;

import jakarta.persistence.*;

@Entity
@Table(name = "product information")
public class productInfo {
    @Id
    @Column
    private int productId;
    @Column
    private int mealId;
    @Column
    private int quantity;

    // Конструкторы
    public productInfo() {}

    public productInfo(int productId, int mealId, int quantity) {
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
