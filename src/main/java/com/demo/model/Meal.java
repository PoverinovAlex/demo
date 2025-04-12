package com.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private LocalDateTime date;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInfo> productInfos = new ArrayList<>();

    // Конструкторы, геттеры и сеттеры

    public Meal(){

    }

    public Meal(User user, List<ProductInfo> productInfos, LocalDateTime date, String name){
        this.user = user;
        this.date = date;
        this.name = name;
        this.productInfos = productInfos;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public int GetId(){
        return id;
    }

    public LocalDateTime GetDate(){
        return date;
    }

    public String GetName(){
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void SetId(int Id){
        id = Id;
    }

    public void SetDate (LocalDateTime d){
        date = d;
    }

    public void SetName(String n){
        name = n;
    }
}
