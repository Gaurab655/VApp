package vapp.dto.responseDto;

import vapp.entity.AccountEntity;
import vapp.entity.UserEntity;
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


    public static UserResponseDto fromEntity(UserEntity userEntity, ModelMapper modelMapper) {
        AccountEntity accountEntity = userEntity.getAccount();
        UserResponseDto userDto = modelMapper.map(userEntity, UserResponseDto.class);
        AccountResponseDto accountDto = modelMapper.map(accountEntity, AccountResponseDto.class);

        userDto.setAccountInformation(accountDto);

        return userDto;
    }

}
