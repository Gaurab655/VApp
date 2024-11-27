package VApp.VApp.controller;

import VApp.VApp.dto.LoginUser;
import VApp.VApp.dto.RegisterAndAccountDto;
import VApp.VApp.services.UserAndAccountServices;
import VApp.VApp.services.UserServices;
import VApp.VApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserAndAccountServices userAndAccountServices;


    @GetMapping
    public List<User> getUsers(){
        return userServices.getUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
       return  userServices.findById(id);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id){
         return userServices.deleteById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
       userServices.updateByEmail(user);
       return user;
    }

}

