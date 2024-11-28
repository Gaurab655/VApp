package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.NotEmpty;

public class LoginUser {
    @NotEmpty
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
