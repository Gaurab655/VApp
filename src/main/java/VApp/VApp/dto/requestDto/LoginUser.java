package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
