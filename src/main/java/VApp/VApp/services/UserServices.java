package VApp.VApp.services;

import VApp.VApp.dto.LoginUser;
import VApp.VApp.dto.UserDto;
import VApp.VApp.entity.UserEntity;
import VApp.VApp.userRepository.UserRepository;
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

    public ResponseEntity<UserDto> newUser(UserDto userDto){
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(userDto.getPassword());
            userRepository.save(userEntity);
           return new ResponseEntity<>(userDto,HttpStatus.CREATED);

        }catch (Exception e){
            System.out.println("unable to save " +e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }}

    public List<UserEntity> getUsers(){
        return userRepository.findAll();

    }

    public ResponseEntity<UserEntity> findById(Integer id){
       Optional<UserEntity> userEntity = userRepository.findById(id);
       if (userEntity.isPresent()){
           return new ResponseEntity<>(userEntity.get(),HttpStatus.FOUND);
       }else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }
    public ResponseEntity<Void> deleteById(Integer id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UserEntity> updateById(UserEntity userEntity, Integer id){
        Optional<UserEntity> existingEntity = userRepository.findById(id);
        if (existingEntity.isPresent()){
            existingEntity.get().setEmail(userEntity.getEmail());
            existingEntity.get().setPassword(userEntity.getPassword());
            UserEntity userEntity1= userRepository.save(existingEntity.get());
            return new ResponseEntity<>(userEntity1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<LoginUser> login(LoginUser loginUser) {
        Optional<UserEntity> userCheck = (Optional<UserEntity>) userRepository.findByEmail(loginUser.getEmail());
        if (userCheck.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (userCheck.get().getPassword().equals(loginUser.getPassword())) {
                System.out.println("Login success");
                return new ResponseEntity<>(loginUser,HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
