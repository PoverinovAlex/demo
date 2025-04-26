package com.demo.DTO;


import com.demo.model.User;

public class UserDTO {
    private int id;
    private String login;
    private String role;

    public UserDTO(int id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public UserDTO (User user){
        id = user.getId();
        login = user.getLogin();
        role = user.getRole();
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

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
