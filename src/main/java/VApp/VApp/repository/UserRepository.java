package VApp.VApp.repository;

import VApp.VApp.entity.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(@NotEmpty String email);

    boolean existsByEmail(String email);
}
