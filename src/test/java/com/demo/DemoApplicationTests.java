package com.demo;

import com.demo.model.Product;
import com.demo.model.Meal;
import com.demo.model.ProductInfo;
import com.demo.model.User;
import com.demo.services.MealService;
import com.demo.services.ProductInfoService;
import com.demo.services.ProductService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.demo.repositories.UserRepository;
import com.demo.services.UserService;

import java.util.List;

@SpringBootTest

//@ContextConfiguration(classes = UserService.class)
class DemoApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductInfoService productInfoService;
	@Autowired
	MealService mealService;

	@Autowired
	UserRepository userRepository;

	@Test
	void deleteAllUsers(){
		List<User> users = userService.GetUserRepository().findAll();
		for (User user : users){
			userService.deleteUser(user);
		}
	}


	@Test
	void contextLoads() {
		User sanechka = userService.registerUser("testLogin2", "ROLE_USER", "testPassword", 2);
		assertNotNull(sanechka);
		assertEquals("testLogin2", sanechka.getLogin());
	}

	@Test
	void contextLoads2() { //уточнить
		User test = new User("testLogin3", "testPassword3", "ROLE_ADMIN", null);
		User nikitusya = userService.saveUser(test);
		assertNotNull(nikitusya);
		assertEquals("testLogin3", nikitusya.getLogin());

	}
	@Test
	void contextLoads3(){
		User getUser = userService.GetUserRepository().findById(2);
		assertEquals("testLogin2", getUser.getLogin());
	}

	@Test
	void contextLoads4(){
		User user1 = userService.GetUserRepository().findById(3);
		user1.setLogin("Nikitusya");
		userService.updateUser(user1);
	}

	@Test
	void contextLoads5(){
		List<User> users = userService.GetUserRepository().findByLogin("testLogin2");
		User user = users.getFirst();
		user.setLogin("Sanechka");
		userService.updateUser(user);

	}

	@Test
	void testUpdateRole(){
		User testUser = userService.GetUserRepository().findById(2);
		testUser.setRole("ROLE_ADMIN");
		userService.updateUser(testUser);
	}

	@Test
	void testlab(){
		User user1 = new User("Nikitank", "77777778", "ROLE_USER", null);
		User user2 = new User("Crishtiano", "Siiiiiii", "ROLE_USER", null);

		userService.saveUser(user1);
		userService.saveUser(user2);

		Product product1 = new Product("potato", 2, 0.1f, 17, 77 ,null);
		Product product2 = new Product("carrot", 3, 0.2f, 15, 75, null);
		Product product3 = new Product("onion", 4, 0.3f, 18, 80, null);
		Product product4 = new Product("tomato", 5, 0.4f, 20, 85, null);
		Product product5 = new Product("cucumber", 6, 0.5f, 22, 90, null);
		Product product6 = new Product("apple", 7, 0.6f, 25, 95, null);
		Product product7 = new Product("banana", 8, 0.7f, 28, 100, null);
		Product product8 = new Product("orange", 9, 0.8f, 30, 105, null);
		Product product9 = new Product("pear", 10, 0.9f, 32, 110, null);
		Product product10 = new Product("grape", 11, 1.0f, 35, 115, null);

		productService.saveProduct(product1);
		productService.saveProduct(product2);
		productService.saveProduct(product3);
		productService.saveProduct(product4);
		productService.saveProduct(product5);
		productService.saveProduct(product6);
		productService.saveProduct(product7);
		productService.saveProduct(product8);
		productService.saveProduct(product9);
		productService.saveProduct(product10);

		Meal meal1 = new Meal(user1, null, LocalDateTime.of(2025, 4, 12, 12, 0), "Обед 1");
		Meal meal2 = new Meal(user1, null, LocalDateTime.of(2025, 4, 13, 13, 0), "Завтрак 1");
		Meal meal3 = new Meal(user1, null, LocalDateTime.of(2025, 4, 14, 14, 0), "Завтрак 2");
		Meal meal4 = new Meal(user1, null, LocalDateTime.of(2025, 4, 15, 15, 0), "Обед 2");
		Meal meal5 = new Meal(user1, null, LocalDateTime.of(2025, 4, 16, 16, 0), "Ужин 2");

		mealService.saveMeal(meal1);
		mealService.saveMeal(meal2);
		mealService.saveMeal(meal3);
		mealService.saveMeal(meal4);
		mealService.saveMeal(meal5);

		// Создаем объекты ProductInfo
		ProductInfo productInfo1 = new ProductInfo(100, meal1, product1);
		ProductInfo productInfo2 = new ProductInfo(100, meal2, product2);
		ProductInfo productInfo3 = new ProductInfo(100, meal2, product3);
		ProductInfo productInfo4 = new ProductInfo(100, meal2, product4);
		ProductInfo productInfo5 = new ProductInfo(100, meal3, product5);
		ProductInfo productInfo6 = new ProductInfo(100, meal3, product6);
		ProductInfo productInfo7 = new ProductInfo(100, meal4, product7);
		ProductInfo productInfo8 = new ProductInfo(100, meal4, product8);
		ProductInfo productInfo9 = new ProductInfo(100, meal5, product9);
		ProductInfo productInfo10 = new ProductInfo(100, meal5, product10);

		productInfoService.saveProductInfo(productInfo1);
		productInfoService.saveProductInfo(productInfo2);
		productInfoService.saveProductInfo(productInfo3);
		productInfoService.saveProductInfo(productInfo4);
		productInfoService.saveProductInfo(productInfo5);
		productInfoService.saveProductInfo(productInfo6);
		productInfoService.saveProductInfo(productInfo7);
		productInfoService.saveProductInfo(productInfo8);
		productInfoService.saveProductInfo(productInfo9);
		productInfoService.saveProductInfo(productInfo10);

		// Инициализируем списки и добавляем в них объекты ProductInfo
		List<ProductInfo> l1 = new ArrayList<>();
		l1.add(productInfo1);
		product1.setProductInfos(l1);

		List<ProductInfo> l2 = new ArrayList<>();
		l2.add(productInfo2);
		product2.setProductInfos(l2);

		List<ProductInfo> l3 = new ArrayList<>();
		l3.add(productInfo3);
		product3.setProductInfos(l3);

		List<ProductInfo> l4 = new ArrayList<>();
		l4.add(productInfo4);
		product4.setProductInfos(l4);

		List<ProductInfo> l5 = new ArrayList<>();
		l5.add(productInfo5);
		product5.setProductInfos(l5);

		List<ProductInfo> l6 = new ArrayList<>();
		l6.add(productInfo6);
		product6.setProductInfos(l6);

		List<ProductInfo> l7 = new ArrayList<>();
		l7.add(productInfo7);
		product7.setProductInfos(l7);

		List<ProductInfo> l8 = new ArrayList<>();
		l8.add(productInfo8);
		product8.setProductInfos(l8);

		List<ProductInfo> l9 = new ArrayList<>();
		l9.add(productInfo9);
		product9.setProductInfos(l9);

		List<ProductInfo> l10 = new ArrayList<>();
		l10.add(productInfo10);
		product10.setProductInfos(l10);

		// Создаем список для meal и добавляем в него объекты ProductInfo
		List<ProductInfo> m1 = new ArrayList<>();
		m1.add(productInfo1);
		m1.add(productInfo2);
		meal1.setProductInfos(m1);

		List<ProductInfo> m2 = new ArrayList<>();
		m2.add(productInfo3);
		m2.add(productInfo4);
		meal2.setProductInfos(m2);

		List<ProductInfo> m3 = new ArrayList<>();
		m3.add(productInfo5);
		m3.add(productInfo6);
		meal3.setProductInfos(m3);

		List<ProductInfo> m4 = new ArrayList<>();
		m4.add(productInfo7);
		m4.add(productInfo8);
		meal4.setProductInfos(m4);

		List<ProductInfo> m5 = new ArrayList<>();
		m5.add(productInfo9);
		m5.add(productInfo10);
		meal5.setProductInfos(m5);

		List<Meal> u1 = new ArrayList<>();
		u1.add(meal1);
		u1.add(meal2);
		user1.setMeals(u1);
		meal1.setUser(user1);
		meal2.setUser(user1);

		List<Meal> u2 = new ArrayList<>();
		u2.add(meal3);
		u2.add(meal4);
		u2.add(meal5);
		user2.setMeals(u2);
		meal3.setUser(user2);
		meal4.setUser(user2);
		meal5.setUser(user2);

		userService.updateUser(user1);
		userService.updateUser(user2);

		productService.updateProduct(product1);
		productService.updateProduct(product2);
		productService.updateProduct(product3);
		productService.updateProduct(product4);
		productService.updateProduct(product5);
		productService.updateProduct(product6);
		productService.updateProduct(product7);
		productService.updateProduct(product8);
		productService.updateProduct(product9);
		productService.updateProduct(product10);

		mealService.updateMeal(meal1);
		mealService.updateMeal(meal2);
		mealService.updateMeal(meal3);
		mealService.updateMeal(meal4);
		mealService.updateMeal(meal5);

		productInfoService.updateProductInfo(productInfo1);
		productInfoService.updateProductInfo(productInfo2);
		productInfoService.updateProductInfo(productInfo3);
		productInfoService.updateProductInfo(productInfo4);
		productInfoService.updateProductInfo(productInfo5);
		productInfoService.updateProductInfo(productInfo6);
		productInfoService.updateProductInfo(productInfo7);
		productInfoService.updateProductInfo(productInfo8);
		productInfoService.updateProductInfo(productInfo9);
		productInfoService.updateProductInfo(productInfo10);
	}


}
