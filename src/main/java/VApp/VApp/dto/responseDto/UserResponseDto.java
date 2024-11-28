package VApp.VApp.dto.responseDto;

import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserResponseDto {
    private int id;
    private String email;
    private List<String> roles;
    private AccountResponseDto accountInformation;

    public static UserResponseDto fromEntity(User user, ModelMapper modelMapper) {
        Account account = user.getAccount();
        UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
        AccountResponseDto accountDto =  modelMapper.map(account, AccountResponseDto.class);

        userDto.setAccountInformation(accountDto);

        return userDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountResponseDto getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountResponseDto accountInformation) {
        this.accountInformation = accountInformation;
    }
}
