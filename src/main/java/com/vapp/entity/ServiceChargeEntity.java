package com.vapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.vapp.enums.ServiceChargeTypeEnum;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_charge")
public class ServiceChargeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "min_amount")
    private BigDecimal minAmount;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    private BigDecimal charge;

    @Enumerated(EnumType.STRING)
    private ServiceChargeTypeEnum type;

}

