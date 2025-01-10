package com.vapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.vapp.enums.ServiceChargeTypeEnum;

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
    private double minAmount;

    @Column(name = "max_amount")
    private double maxAmount;

    private double charge;

    @Enumerated(EnumType.STRING)
    private ServiceChargeTypeEnum type;

}

