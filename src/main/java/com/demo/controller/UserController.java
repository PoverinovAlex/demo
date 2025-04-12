package com.demo.controller;

import java.util.List;

import com.demo.model.User;
import com.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Создание пользователя
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.GetUserRepository().save(user);
    }

    // Получение пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.GetUserRepository().findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // Получение всех пользователей
    @GetMapping
    public List<User> getAllUsers() {
        return userService.GetUserRepository().findAll();
    }

    // Обновление пользователя
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        return userService.GetUserRepository().findById(id)
                .map(user -> {
                    user.setLogin(userDetails.getLogin());
                    user.setPassword(userDetails.getPassword());
                    User updatedUser = userService.GetUserRepository().save(user);
                    return ResponseEntity.ok().body(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление пользователя
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return userService.GetUserRepository().findById(id)
                .map(user -> {
                    userService.deleteUser(user);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
