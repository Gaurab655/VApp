package vapp.repository;

import vapp.entity.AccountEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByAccountNumber(@NotNull Long accountNumber);

    @Query("SELECT MAX(a.accountNumber) FROM AccountEntity a")
    Optional<Long> findMaxAccountNumber();
}
