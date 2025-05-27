package com.demo.controller;

import com.demo.DTO.ProductDTO;
import com.demo.DTO.UserDTO;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repositories.ProductRepository;
import com.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Создание продукта
    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products/";
    }

    // Получение продукта по ID
    @GetMapping("/{id}")
    public String getProductById(@PathVariable(name = "id") int id, Model model) {
        Product product = productService.GetProductRepository().findById(id);
        if (product != null) {
            model.addAttribute("product", new ProductDTO(product));
            return "product-detail"; // Представление product-detail.html
        } else {
            return "not-found"; // Представление not-found.html
        }
    }

    // Главная страница с продуктами
    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.GetProductRepository().findAll();
        model.addAttribute("products", products);
        return "index"; // Представление index.html
    }

    // Получение продуктов по диапазону белков
    @GetMapping("/proteins/{startPro}/{endPro}")
    public String getProductsByProteins(@PathVariable float startPro, @PathVariable float endPro, Model model) {
        List<Product> products = productService.GetProductRepository().findByProteinsBetween(startPro, endPro);
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOList);
        return "products-list"; // Представление products-list.html
    }

    // Получение продуктов по диапазону жиров
    @GetMapping("/fats/{startFat}/{endFat}")
    public String getProductsByFats(@PathVariable float startFat, @PathVariable float endFat, Model model) {
        List<Product> products = productService.GetProductRepository().findByFatsBetween(startFat, endFat);
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOList);
        return "products-list"; // Представление products-list.html
    }

    // Получение продуктов по диапазону углеводов
    @GetMapping("/carbohydrates/{startCar}/{endCar}")
    public String getProductsByCarbohydrates(@PathVariable float startCar, @PathVariable float endCar, Model model) {
        List<Product> products = productService.GetProductRepository().findByCarbohydratesBetween(startCar, endCar);
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOList);
        return "products-list"; // Представление products-list.html
    }

    // Получение продуктов по диапазону калорий
    @GetMapping("/calories/{startCal}/{endCal}")
    public String getProductsByCalories(@PathVariable float startCal, @PathVariable float endCal, Model model) {
        List<Product> products = productService.GetProductRepository().findByCaloriesBetween(startCal, endCal);
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOList);
        return "products-list"; // Представление products-list.html
    }

    // Получение всех продуктов
    @GetMapping
    public String getAllProduct(Model model) {
        List<Product> products = productService.GetProductRepository().findAll();
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOList);
        return "products-list"; // Представление products-list.html
    }

    // Обновление продукта
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute Product productDetails) {
        Optional<Product> productOptional = productService.GetProductRepository().findById(id);
        Product defaultProduct = new Product(); // Создайте объект по умолчанию
        Product product = productOptional.orElse(defaultProduct);

        if (product != null) {
            product.setName(productDetails.getName());
            product.setProteins(productDetails.getProteins());
            product.setFats(productDetails.getFats());
            product.setCarbohydrates(productDetails.getCarbohydrates());
            product.setCalories(productDetails.getCalories());

            productService.GetProductRepository().save(product);
        }
        return "redirect:/products/";
    }

    // Удаление продукта
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Integer id) {
        Optional<Product> productOptional = productService.GetProductRepository().findById(id);
        Product defaultProduct = new Product(); // Создайте объект по умолчанию
        Product product = productOptional.orElse(defaultProduct);

        if (product != null) {
            productService.GetProductRepository().delete(product);
        }
        return "redirect:/products/";
    }
}
