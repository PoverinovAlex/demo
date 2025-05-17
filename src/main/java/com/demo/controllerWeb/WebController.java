package com.demo.controllerWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class WebController {
    @GetMapping
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "home"; // Шаблон home.html (Thymeleaf)
    }
    @GetMapping("/user/dashboard")
    public String userDashboard(Model model, Principal principal) {
        model.addAttribute("message", "Добро пожаловать, " + principal.getName());
        return "user/dashboard";
    }
    @GetMapping("/admin/panel")
    public String adminPanel(Model model, Principal principal) {
        model.addAttribute("message", "Админ-панель: " + principal.getName());
        return "admin/panel";
    }
}
