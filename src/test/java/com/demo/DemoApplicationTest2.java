package com.demo;

import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repositories.ProductRepository;
import com.demo.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;

@SpringBootTest
public class DemoApplicationTest2 {

}
