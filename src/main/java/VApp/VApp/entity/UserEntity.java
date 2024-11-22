package VApp.VApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private AccountEntity accountEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
