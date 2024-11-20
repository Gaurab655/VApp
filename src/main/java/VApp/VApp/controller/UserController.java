package VApp.VApp.controller;

import VApp.VApp.dto.LoginUser;
import VApp.VApp.dto.RegisterAndAccountDto;
import VApp.VApp.services.UserServices;
import VApp.VApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<RegisterAndAccountDto> createUser(@RequestBody RegisterAndAccountDto registerAndAccountDto){
        return userServices.newUser(registerAndAccountDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser){
        return userServices.login(loginUser);
    }
    @GetMapping
    public List<UserEntity> getUsers(){
        return userServices.getUsers();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Integer id){
       return  userServices.findById(id);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id){
         return userServices.deleteById(id);
    }
    @PutMapping("/id/{id}")
    public UserEntity updateUser(@RequestBody UserEntity userEntity , @PathVariable Integer id){
       userServices.updateById(userEntity, id);
       return userEntity;
    }

}

