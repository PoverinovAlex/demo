package com.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private Date date;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "id.meal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInfo> productInfos = new ArrayList<>();

    // Конструкторы, геттеры и сеттеры

    public Meal(){

    }

    public Meal(int id, int userId, Date date, String name){
        this.id = id;
        //this.userId = userId;
        this.date = date;
        this.name = name;
    }

    public int GetId(){
        return id;
    }

    public Date GetDate(){
        return date;
    }

    public String GetName(){
        return name;
    }

    public void SetId(int Id){
        id = Id;
    }

    public void SetDate (Date d){
        date = d;
    }

    public void SetName(String n){
        name = n;
    }
}
