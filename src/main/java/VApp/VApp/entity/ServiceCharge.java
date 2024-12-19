package VApp.VApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_charges")
public class ServiceCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_amount", nullable = false)
    private double minAmount;

    @Column(name = "max_amount", nullable = false)
    private double maxAmount;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private String type;

}

