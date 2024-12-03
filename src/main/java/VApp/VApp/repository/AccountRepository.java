package VApp.VApp.repository;

import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNumber(@NotNull Long accountNumber);
}
