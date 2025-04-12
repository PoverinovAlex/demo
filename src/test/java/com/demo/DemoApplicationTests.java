package com.demo;

import com.demo.model.User;
import org.junit.jupiter.api.Test;
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
		User test = new User(3, "testLogin3", "testPassword3", "ROLE_ADMIN");
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

}
