package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAndAccountDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private double balance;
    @NotNull
    @Min(value = 1000,message = "PIN must be at least 1000")
    @Max(value = 9999,message = "PIN must be at most 9999")
    private Integer pin;
    @NotNull
    private List<String> roles;
}
