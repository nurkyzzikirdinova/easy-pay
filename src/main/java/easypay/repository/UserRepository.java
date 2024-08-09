package easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zikirdinova.easypay.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);
}
