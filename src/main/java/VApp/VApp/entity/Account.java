package VApp.VApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private Long accountNumber;

    private String fullName;
    private double balance;
    private Integer pin;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
