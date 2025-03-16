package com.demo;

import com.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.demo.repositories.UserRepository;
import com.demo.services.UserService;

@SpringBootTest
//@ContextConfiguration(classes = UserService.class)
class DemoApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		User registeredUser = userService.registerUser("testLogin", "testPassword", 1);

		assertNotNull(registeredUser);
		assertEquals("testLogin", registeredUser.getLogin());
		assertEquals("encodedPassword", registeredUser.getPassword());
		assertEquals(1, registeredUser.getId());

		verify(userRepository, times(1)).save(any(User.class));
	}

}
