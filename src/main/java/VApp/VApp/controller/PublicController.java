package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.LoginUser;
import VApp.VApp.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    private final UserServices userServices;

    PublicController(UserServices userServices){
        this.userServices=userServices;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws Exception{
        return userServices.login(loginUser);
    }

}
