package VApp.VApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JsonProperty("account")
    private Account account;
    @NotNull
    private List<String> roles;

    public long getId() {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public @NotNull List<String> getRoles() {
        return roles;
    }

    public void setRoles(@NotNull List<String> roles) {
        this.roles = roles;
    }
}
