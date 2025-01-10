package com.vapp.repository;

import com.vapp.entity.ServiceChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceChargeRepository extends JpaRepository<ServiceChargeEntity, Integer> {
    @Query("SELECT sc FROM ServiceChargeEntity sc WHERE :amount BETWEEN sc.minAmount AND sc.maxAmount")
    Optional<ServiceChargeEntity> findByAmountRange(@Param("amount") double amount);
}