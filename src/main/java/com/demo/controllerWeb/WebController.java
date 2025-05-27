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

    @GetMapping
    public String home() {
        return "redirect:/products/"; // Перенаправляем на страницу продуктов
    }
}