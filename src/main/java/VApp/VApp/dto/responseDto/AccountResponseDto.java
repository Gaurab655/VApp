package VApp.VApp.dto.responseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private Long accountNumber;
    private String fullName;
    private double balance;

}
