package services;

import model.User;
import repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import jakarta.persistence.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Репозиторий для работы с базой данных

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Для хеширования паролей

    private EntityManagerFactory emf;

    public UserService() {
        this.emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public User registerUser(String login, String password, int Id) {

        userRepository.findById(Id);
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password)); // Хеширование пароля
        user.setId(Id);

        return userRepository.save(user); // Сохранение пользователя в базе данных
    }

    // метод для сохранения пользователя в базу данных
    public void saveUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    public void updateUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
        entityManager.close();
    }

    public void deleteUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        transaction.commit();
        entityManager.close();
    }



}









