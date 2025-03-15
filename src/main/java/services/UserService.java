package services;

import model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.*;
import java.util.List;

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

    public user registerUser(String login, String password, int Id) {
        user User = new user();
        User.setLogin(login);
        User.setPassword(passwordEncoder.encode(password)); // Хеширование пароля
        User.setId(Id);

        return userRepository.save(User); // Сохранение пользователя в базе данных
    }

    // метод для сохранения пользователя в базу данных
    public void saveUser(user User) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(User);
        transaction.commit();
        entityManager.close();
    }

    public void updateUser(user User) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(User);
        transaction.commit();
        entityManager.close();
    }

    public void deleteUser(user User) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Check if the entity is managed by the EntityManager, and if not, merge it before removal
        entityManager.remove(entityManager.contains(User) ? User : entityManager.merge(User));
        transaction.commit();
        entityManager.close();
    }



}









