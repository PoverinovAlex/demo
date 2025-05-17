package com.demo.repositories;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    List<User> findByLogin(String login);
    List<User> findByRole (String role);
    Optional<User> getSecurityUserByLogin(String login);
    User findById(int id);

}