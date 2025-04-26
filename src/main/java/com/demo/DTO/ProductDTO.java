package com.demo.DTO;


import com.demo.model.Product;

public class ProductDTO {
    private int id;
    private String name;
    private float proteins;
    private float fats;
    private float carbohydrates;
    private float calories;

    public ProductDTO(int id, String name, float proteins, float fats, float carbohydrates, float calories) {
        this.id = id;
        this.name = name;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
    }

    public  ProductDTO (Product product){
        id = product.getId();
        name = product.getName();
        proteins = product.getProteins();
        fats = product.getFats();
        carbohydrates = product.getCarbohydrates();
        calories = product.getCalories();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getProteins() {
        return proteins;
    }

    public float getFats() {
        return fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public float getCalories() {
        return calories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }
}
