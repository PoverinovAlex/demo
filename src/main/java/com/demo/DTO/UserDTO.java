package com.demo.DTO;


import com.demo.model.Meal;
import com.demo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("role")
    private String role;
    @JsonProperty("mealsID")
    List<Integer> mealsID;

    public UserDTO() {
    }

    public UserDTO(int id, String login, String role, List<Integer> mealsID) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.mealsID = mealsID;
    }

    public UserDTO (User user){
        id = user.getId();
        login = user.getLogin();
        role = user.getRole();
        mealsID = new ArrayList<>();

        List<Meal> meals = user.getMeals();
        for(Meal meal : meals){
            mealsID.add(meal.GetId());
        }
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public List<Integer> getMealsID() {
        return mealsID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMealsID(List<Integer> mealsID) {
        this.mealsID = mealsID;
    }
}
