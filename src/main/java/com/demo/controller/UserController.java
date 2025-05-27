package com.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.DTO.AuthDTO;
import com.demo.DTO.UserDTO;
import com.demo.controllerWeb.JWTUtil;
import com.demo.model.User;
import com.demo.services.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestParam String login, @RequestParam String password, Model model) {
        if (login != null && password != null) {
            userService.registerUser(login, "ROLE_USER", password);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    login,
                    password,
                    AuthorityUtils.createAuthorityList("ROLE_USER")
            );
            String token = jwtUtil.createToken(authentication);
            model.addAttribute("token", token);
            return "register-success"; // Представление register-success.html
        } else {
            model.addAttribute("error", "Login and password are required");
            return "register-error"; // Представление register-error.html
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, Model model) {
        User user = userService.GetUserRepository().findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    login,
                    password,
                    AuthorityUtils.createAuthorityList("ROLE_USER")
            );
            String token = jwtUtil.createToken(authentication);
            model.addAttribute("token", token);
            return "login-success"; // Представление login-success.html
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "login-error"; // Представление login-error.html
        }
    }

    @GetMapping("/getMeals")
    @PreAuthorize("isAuthenticated()")
    public String getUserMeals(Principal principal, Model model) {
        UserDTO userDTO = getUserDTOForPrincipal(principal);
        model.addAttribute("meals", userDTO.getMealsID());
        return "meals-list"; // Представление meals-list.html
    }

    private UserDTO getUserDTOForPrincipal(Principal principal) {
        User user = userService.GetUserRepository().findByLogin(principal.getName());
        return new UserDTO(user);
    }

    @PostMapping
    public String createUser(@ModelAttribute AuthDTO authDTO) {
        User user = new User(authDTO.getLogin(), authDTO.getPassword(), "ROLE_USER", null);
        userService.GetUserRepository().save(user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.GetUserRepository().findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", new UserDTO(user.get()));
            return "user-detail"; // Представление user-detail.html
        } else {
            return "not-found"; // Представление not-found.html
        }
    }

    @GetMapping("/getAll")
    public String getAllUsers(Model model) {
        List<User> users = userService.GetUserRepository().findAll();
        List<UserDTO> userDTOList = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("users", userDTOList);
        return "users-list"; // Представление users-list.html
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute User userDetails) {
        userService.GetUserRepository().findById(id)
                .ifPresent(user -> {
                    user.setLogin(userDetails.getLogin());
                    user.setPassword(userDetails.getPassword());
                    userService.GetUserRepository().save(user);
                });
        return "redirect:/users/";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Integer id) {
        userService.GetUserRepository().findById(id)
                .ifPresent(userService::deleteUser);
        return "redirect:/users/";
    }
}
