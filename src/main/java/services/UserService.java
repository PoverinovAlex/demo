package services;

import model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Репозиторий для работы с базой данных

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Для хеширования паролей

    public user registerUser(String login, String password, int Id) {
        user User = new user();
        User.setLogin(login);
        User.setPassword(passwordEncoder.encode(password)); // Хеширование пароля
        User.setId(Id);

        return userRepository.save(User); // Сохранение пользователя в базе данных
    }
}