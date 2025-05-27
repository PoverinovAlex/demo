package com.demo.controllerWeb;

import com.demo.model.Product;
import com.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private ProductService productService;

    private List<Product> products = new ArrayList<>();
    private Map<String, Integer> selectedProducts = new HashMap<>();
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

        int totalCalories = 0;
        for (Product product : products) {
            int calories = (int) product.getCalories();
            totalCalories += calories;
        }
        model.addAttribute("totalCalories", totalCalories);

        return "index";
    }

    @PostMapping("/selectProduct")
    public String selectProduct(@RequestParam String name, @RequestParam int calories, @RequestParam int protein, @RequestParam int carbs, @RequestParam int fats) {
        selectedProducts.merge(name, 1, Integer::sum);
        return "redirect:/";
    }

    @PostMapping("/deselectProduct")
    public String deselectProduct(@RequestParam String name) {
        if (selectedProducts.containsKey(name)) {
            int quantity = selectedProducts.get(name);
            if (quantity > 1) {
                selectedProducts.put(name, quantity - 1);
            } else {
                selectedProducts.remove(name);
            }
        }
        return "redirect:/";
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
