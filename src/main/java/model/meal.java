package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meals")
public class meal {

    @Id
    private int id;
    private int userId;
    private Date date;
    private String name;

    // Конструкторы, геттеры и сеттеры
}
