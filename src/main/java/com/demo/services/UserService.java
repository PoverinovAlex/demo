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
        user.setPassword(passwordEncoder.encode(password)); // Хеширование пароля
        user.setId(Id);

        return userRepository.save(user); // Сохранение пользователя в базе данных
    }

    @Transactional
    public User saveUser(User user) {
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
                    oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
                }
                if (user.getRole() != null){
                    oldUser.setRole(user.getRole());
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


// метод для сохранения пользователя в базу данных
  /*  public void saveUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }*/

    /*public void updateUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
        entityManager.close();
    }*/


    /*@Transactional
    public void deleteUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        transaction.commit();
        entityManager.close();
    }*/










