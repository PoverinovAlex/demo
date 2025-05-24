package com.demo.controllerWeb;

import com.demo.model.Product;
import com.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private ProductService productService;
/*    @GetMapping
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index"; // Шаблон index.html (Thymeleaf)
    }*/

    @GetMapping
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        List<Product> products = productService.GetProductRepository().findAll();
        model.addAttribute("products", products);

        // Пример вычисления суммы калорий
        int totalCalories = 0;
        for (Product product : products) {
            int calories = (int) product.getCalories();
            totalCalories += calories;
        }
        model.addAttribute("totalCalories", totalCalories);

        return "index";
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
