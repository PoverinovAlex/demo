package com.demo.DTO;

import com.demo.model.Meal;
import com.demo.model.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class MealDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("date")
    private LocalDateTime date;
    @JsonProperty("name")
    private String name;
    @JsonProperty("userID")
    private int userID;
    @JsonProperty("productsID")
    private List<Integer> productsID;

    public MealDTO (Meal meal){
        id = meal.GetId();
        name = meal.GetName();
        date = meal.GetDate();
        userID = meal.getUser().getId();

        //List<ProductInfo>
    }

    public MealDTO() {
    }

    public MealDTO(String name, LocalDateTime date, int userID, List<Integer> productsID) {
        this.name = name;
        this.date = date;
        this.userID = userID;
        this.productsID = productsID;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public List<Integer> getProductsID() {
        return productsID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setProductsID(List<Integer> productsID) {
        this.productsID = productsID;
    }
}
