package VApp.VApp.controller;

import VApp.VApp.dto.responseDto.UserResponseDto;
import VApp.VApp.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserServices userServices;

    AdminController(UserServices userServices){
        this.userServices=userServices;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return userServices.getUsers();
    }

}
