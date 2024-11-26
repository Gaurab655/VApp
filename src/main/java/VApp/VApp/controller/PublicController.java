package VApp.VApp.controller;

import VApp.VApp.dto.LoginUser;
import VApp.VApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserServices userServices;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser){
        return userServices.login(loginUser);
    }
}
