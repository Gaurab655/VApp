package VApp.VApp.controller;

import VApp.VApp.dto.responseDto.UserResponseDto;
//import VApp.VApp.service.AdminServices;
import VApp.VApp.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserServices userServices;
//    private final AdminServices adminServices;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return userServices.getUsers();
    }
//    @PostMapping
//    public ResponseEntity<String> serviceCharges(){
//
//    }

}
