package model;


import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class user {

    @Id // обозначение первичного ключа
    private int id;
    private String login;
    private String password;

    // Конструкторы, геттеры и сеттеры
}