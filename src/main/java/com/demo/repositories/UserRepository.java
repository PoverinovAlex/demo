package com.demo.repositories;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    List<User> findByLogin(String login);
    List<User> findByRole (String role);
    User findById(int id);

}