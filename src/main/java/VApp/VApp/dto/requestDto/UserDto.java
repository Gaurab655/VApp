package VApp.VApp.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String password;

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }




}
