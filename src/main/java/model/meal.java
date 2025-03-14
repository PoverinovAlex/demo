package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meals")
public class meal {

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
    private user User;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<productInfo> productInfos;

    // Конструкторы, геттеры и сеттеры

    meal(){

    }

    meal(int id, int userId, Date date, String name){
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
