package VApp.VApp.repository;

import VApp.VApp.entity.ServiceCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ServiceChargeRepo extends JpaRepository<ServiceCharge, Integer> {

    @Query(value = "SELECT * FROM service_charges WHERE :amount >= CAST(SPLIT_PART(amount_range, '-', 1) AS double precision) " +
            "AND :amount <= CAST(SPLIT_PART(amount_range, '-', 2) AS double precision)",
            nativeQuery = true)
    Optional<ServiceCharge> findByAmountRange(@Param("amount") double amount);
}