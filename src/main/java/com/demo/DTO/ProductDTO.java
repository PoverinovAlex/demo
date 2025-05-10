package com.demo.DTO;


import com.demo.model.Product;
import com.demo.model.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("proteins")
    private float proteins;
    @JsonProperty("fats")
    private float fats;
    @JsonProperty("carbohydrates")
    private float carbohydrates;
    @JsonProperty("calories")
    private float calories;
    @JsonProperty("productinfosID")
    private List<Integer> productinfosID;


    public ProductDTO() {
    }

    public ProductDTO(int id, String name, float proteins, float fats, float carbohydrates, float calories, List<Integer> productInfosID) {
        this.id = id;
        this.name = name;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.productinfosID = productInfosID;
    }

    public  ProductDTO (Product product){
        id = product.getId();
        name = product.getName();
        proteins = product.getProteins();
        fats = product.getFats();
        carbohydrates = product.getCarbohydrates();
        calories = product.getCalories();
        productinfosID = new ArrayList<>();

        List<ProductInfo> productInfos = product.getProductInfos();
        for (ProductInfo productInfo : productInfos){
            productinfosID.add(productInfo.getId());
        }
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

    public List<Integer> getProductinfosID() {
        return productinfosID;
    }

    public void setProductinfosID(List<Integer> productinfosID) {
        this.productinfosID = productinfosID;
    }
}
