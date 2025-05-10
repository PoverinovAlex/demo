package com.demo.DTO;

import com.demo.model.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductInfoDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("MealID")
    private int mealID;
    @JsonProperty("ProductID")
    private int productID;

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(int id, int quantity, int mealID, int productID) {
        this.id = id;
        this.quantity = quantity;
        this.mealID = mealID;
        this.productID = productID;
    }
    public ProductInfoDTO(ProductInfo productInfo){
        id = productInfo.getId();
        quantity = productInfo.getQuantity();
        mealID = productInfo.getMeal().GetId();
        productID = productInfo.getProduct().getId();
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMealID() {
        return mealID;
    }

    public int getProductID() {
        return productID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
