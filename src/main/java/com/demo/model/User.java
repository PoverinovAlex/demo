package com.demo.model;

import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id // обозначение первичного ключа
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    @Column
    private String password;
    @Column
    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meal> meals;
    // Конструкторы
    public User() {}

    public User(String login, String password, String role, List<Meal> meals) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.meals = meals;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String r){
        role = r;
    }
}