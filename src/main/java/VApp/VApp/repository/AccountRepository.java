package VApp.VApp.repository;

import VApp.VApp.entity.Account;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(@NotNull Long accountNumber);

    @Query("SELECT MAX(a.accountNumber) FROM Account a")
    Optional<Long> findMaxAccountNumber();
}
