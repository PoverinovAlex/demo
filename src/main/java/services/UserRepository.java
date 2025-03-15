package services;
import model.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Long> {
    user findByEmail(String login); // Метод для поиска пользователя по email
}
