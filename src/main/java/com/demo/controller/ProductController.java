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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Создание продукта
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    // Получение продукта по ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") int id){
        Product product = productService.GetProductRepository().findById(id);
        ProductDTO productDTO = new ProductDTO(product);

        return productDTO != null
                ? new ResponseEntity<>(productDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = new ArrayList<>();
        // Добавьте ваши продукты в список
        products.add(new Product("Банан", 5, 10, 20, 90, "https://steamuserimages-a.akamaihd.net/ugc/831329213371371783/06B84E6FC604FC50991016819976341F6BB1E6EB/?imw=512&amp;imh=384&amp;ima=fit&amp;impolicy=Letterbox&amp;imcolor=%23000000&amp;letterbox=true"));
        // Добавьте остальные продукты аналогично
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/proteins/{startPro}/{endPro}")
    public ResponseEntity<List<ProductDTO>> getProductsByProteins(@PathVariable float startPro, @PathVariable float endPro) {
        List<Product> products = productService.GetProductRepository().findByProteinsBetween(startPro, endPro);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }
        if (!productDTOList.isEmpty()) {
            return ResponseEntity.ok().body(productDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fats/{startFat}/{endFat}")
    public ResponseEntity<List<ProductDTO>> getProductsByFats(@PathVariable float startFat, @PathVariable float endFat) {
        List<Product> products = productService.GetProductRepository().findByFatsBetween(startFat, endFat);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }
        if (!productDTOList.isEmpty()) {
            return ResponseEntity.ok().body(productDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carbohydrates/{startCar}/{endCar}")
    public ResponseEntity<List<ProductDTO>> getProductsByCarbohydrates(@PathVariable float startCar, @PathVariable float endCar) {
        List<Product> products = productService.GetProductRepository().findByCarbohydratesBetween(startCar, endCar);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }
        if (!productDTOList.isEmpty()) {
            return ResponseEntity.ok().body(productDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/calories/{startCal}/{endCal}")
    public ResponseEntity<List<ProductDTO>> getProductsByCalories(@PathVariable float startCal, @PathVariable float endCal) {
        List<Product> products = productService.GetProductRepository().findByCaloriesBetween(startCal, endCal);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }
        if (!productDTOList.isEmpty()) {
            return ResponseEntity.ok().body(productDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Получение всех продуктов
    @GetMapping
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productService.GetProductRepository().findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    // Обновление продукта
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        Optional<Product> productOptional = productService.GetProductRepository().findById(id);

        Product defaultProduct = new Product(); // Создайте объект по умолчанию
        Product product = productOptional.orElse(defaultProduct);

        if (product != null) {
            product.setName(productDetails.getName());
            product.setProteins(productDetails.getProteins());
            product.setFats(productDetails.getFats());
            product.setCarbohydrates(productDetails.getCarbohydrates());
            product.setCalories(productDetails.getCalories());

            Product updatedProduct = productService.GetProductRepository().save(product);
            return ResponseEntity.ok().body(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {

        Optional<Product> productOptional = productService.GetProductRepository().findById(id);
        Product defaultProduct = new Product(); // Создайте объект по умолчанию
        Product product = productOptional.orElse(defaultProduct);

        if (product != null) {
            productService.GetProductRepository().delete(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
