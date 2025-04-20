package com.demo.services;

import com.demo.model.User;
import com.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


import jakarta.persistence.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Репозиторий для работы с базой данных

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Для хеширования паролей

    //private EntityManagerFactory emf;

    public UserService() {
        //this.emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public User registerUser(String login, String role, String password, int Id) {

        userRepository.findById(Id);
        User user = new User();
        user.setLogin(login);
        user.setRole(role);
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword); // Хеширование пароля
        user.setId(Id);

        return userRepository.save(user); // Сохранение пользователя в базе данных
    }

    @Transactional
    public User saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }


    @Transactional
    public void updateUser(User user) {
        try {
            User oldUser = userRepository.findById(user.getId());
                if (user.getLogin() != null) {
                    oldUser.setLogin(user.getLogin());
                }
                if (user.getPassword() != null) {
                    String hashedPassword = passwordEncoder.encode(user.getPassword());
                    oldUser.setPassword(hashedPassword);
                }
                if (user.getRole() != null){
                    oldUser.setRole(user.getRole());
                }
                if(user.getMeals()!= null){
                    oldUser.setMeals(user.getMeals());
                }
                userRepository.save(oldUser);
        } catch (RuntimeException e) {
            // Логирование ошибки или выполнение альтернативных действий
            System.err.println("Error updating user: " + e.getMessage());
            throw e; // Перевыброс исключения, если необходимо
        }
    }


    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public UserRepository GetUserRepository (){
        return userRepository;
    }
}