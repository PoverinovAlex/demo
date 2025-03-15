package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @Column
    private int id;
    @Column
    private int userId;
    @Column
    private Date date;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private model.User user;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInfo> productInfos;

    // Конструкторы, геттеры и сеттеры

    Meal(){

    }

    Meal(int id, int userId, Date date, String name){
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.name = name;
    }

    int GetId(){
        return id;
    }
    int GetUserId(){
        return userId;
    }
    Date GetDate(){
        return date;
    }
    String GetName(){
        return name;
    }

    void SetId(int Id){
        id = Id;
    }

    void SetUserId (int uId){
        userId = uId;
    }

    void SetDate (Date d){
        date = d;
    }

    void SetName(String n){
        name = n;
    }
}
