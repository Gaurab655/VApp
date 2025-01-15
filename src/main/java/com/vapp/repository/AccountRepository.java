package com.vapp.repository;

import com.vapp.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByAccountNumber(Long accountNumber);

    @Query("SELECT MAX(a.accountNumber) FROM AccountEntity a")
    Optional<Long> findMaxAccountNumber();
}
