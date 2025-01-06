package VApp.VApp.dto.responseDto;

import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseDto {
    private int id;
    private String role;
    private String email;
    private AccountResponseDto accountInformation;


    public static UserResponseDto fromEntity(User user, ModelMapper modelMapper) {
        Account account = user.getAccount();
        UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
        AccountResponseDto accountDto = modelMapper.map(account, AccountResponseDto.class);

        userDto.setAccountInformation(accountDto);

        return userDto;
    }

}
