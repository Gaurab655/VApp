package VApp.VApp.services;

import VApp.VApp.dto.LoginUser;
import VApp.VApp.dto.RegisterAndAccountDto;
import VApp.VApp.dto.UserDto;
import VApp.VApp.entity.AccountEntity;
import VApp.VApp.entity.UserEntity;
import VApp.VApp.exception.BankException;
import VApp.VApp.userRepository.AccountRepository;
import VApp.VApp.userRepository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

//    public ResponseEntity<UserDto> newUser(UserDto userDto) {
//        try {
//            UserEntity userEntity = new UserEntity();
//            userEntity.setEmail(userDto.getEmail());
//            userEntity.setPassword(userDto.getPassword());
//            userRepository.save(userEntity);
//            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
//
//        } catch (Exception e) {
//            System.out.println("unable to save " + e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
    public ResponseEntity<RegisterAndAccountDto> newUser(RegisterAndAccountDto registerAndAccountDto){
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(registerAndAccountDto.getEmail());
            userEntity.setPassword(registerAndAccountDto.getPassword());
            userRepository.save(userEntity);

            AccountEntity accountEntity = new AccountEntity();
            accountRepository.save(accountEntity);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();

    }

    public ResponseEntity<UserEntity> findById(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return new ResponseEntity<>(userEntity.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Void> deleteById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UserEntity> updateById(UserEntity userEntity, Integer id) {
        Optional<UserEntity> existingEntity = userRepository.findById(id);
        if (existingEntity.isPresent()) {
            existingEntity.get().setEmail(userEntity.getEmail());
            existingEntity.get().setPassword(userEntity.getPassword());
            UserEntity userEntity1 = userRepository.save(existingEntity.get());
            return new ResponseEntity<>(userEntity1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> login(LoginUser loginUser) {
        Optional<UserEntity> userCheck = userRepository.findByEmail(loginUser.getEmail());
        if (userCheck.isEmpty()) {
            System.out.println("Email not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (userCheck.get().getPassword().equals(loginUser.getPassword())){
                System.out.println("user found");
                return new ResponseEntity<>(loginUser,HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
}
