package com.demo.DTO;

import java.time.LocalDateTime;

public class MealDTO {

    private int id;
    private LocalDateTime date;
    private String name;

    public MealDTO(String name, LocalDateTime date, int id) {
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
