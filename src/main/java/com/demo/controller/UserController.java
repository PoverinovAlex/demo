package com.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.demo.DTO.AuthDTO;
import com.demo.DTO.UserDTO;
import com.demo.controllerWeb.JWTUtil;
import com.demo.model.User;
import com.demo.services.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String login, @RequestParam String password) {
        if (login != null && password != null) {
            userService.registerUser(login, "ROLE_USER", password);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        login,
                        password,
                        AuthorityUtils.createAuthorityList("ROLE_USER")
                );
                String token = jwtUtil.createToken(authentication);
            return ResponseEntity.ok("User registered successfully. Token: " + token);
        } else {
            return ResponseEntity.badRequest().body("Login and password are required");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String login, @RequestParam String password) {
        User user = userService.GetUserRepository().findByLogin(login);
        if (user != null) {
            if(user.getPassword().equals(password)) {
                if (user.getPassword().equals(password)) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            login,
                            password,
                            AuthorityUtils.createAuthorityList("ROLE_USER")
                    );
                    String token = jwtUtil.createToken(authentication);
                    return ResponseEntity.ok("User logged in successfully. Token: " + token);
                }
                else{
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
                }
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password or login");
    }

    // Создание пользователя
    @PostMapping
    public User createUser(@RequestBody AuthDTO authDTO) {
        User user = new User(authDTO.getLogin(), authDTO.getPassword(), "ROLE_USER", null);
        return userService.GetUserRepository().save(user);
    }

    // Получение пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {

        Optional<User> user = userService.GetUserRepository().findById(id);
        if (user.isPresent()){
            UserDTO userDTO = new UserDTO(user.get());

            return userDTO != null
                    ? new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return null;
        }
    }

    // Получение всех пользователей
    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers() {

        List<User> users = userService.GetUserRepository().findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users){
            UserDTO userDTO = new UserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
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