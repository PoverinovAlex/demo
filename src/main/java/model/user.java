package model;

import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class user {

    @Id // обозначение первичного ключа
    private int id;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<meal> meals;
    // Конструкторы
    public user() {}

    public user(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
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
}