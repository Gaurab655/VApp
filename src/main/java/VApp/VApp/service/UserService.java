//package VApp.VApp.service;
//
//import VApp.VApp.dto.requestDto.LoginUser;
//import VApp.VApp.dto.responseDto.UserResponseDto;
//import VApp.VApp.entity.User;
//import VApp.VApp.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.util.*;
//
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;
//
//    public ResponseEntity<List<UserResponseDto>> getUsers() {
//        List<User> users = userRepository.findAll();
//        List<UserResponseDto> result = users.stream().map((user) -> UserResponseDto.fromEntity(user, modelMapper)).toList();
//        return new ResponseEntity<>(result, HttpStatus.FOUND);
//    }}

//    public ResponseEntity<Void> deleteById(Integer id) {
//        if (userRepository.existsById(id)) {
//            userRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    public ResponseEntity<User> updateByEmail(User user) throws Exception{
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User existingUser = userRepository.findByEmail(email)
//                            .orElseThrow(()-> new Exception("User not found with email : "+email));
//            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
//                existingUser.setEmail(user.getEmail());
//                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
//                User updatedUser = userRepository.save(existingUser);
//
//                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//    }
//
//
//    public ResponseEntity<String> login(LoginUser loginUser) throws Exception {
//        User userCheck = userRepository.findByEmail(loginUser.getEmail())
//                .orElseThrow(() -> new Exception("User not found with email : " + loginUser.getEmail()));
//
//        if (userCheck.getPassword().equals(loginUser.getPassword())) {
//            System.out.println("user found");
//            return new ResponseEntity<>("User Found", HttpStatus.FOUND);
//        }else {
//            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
//        }
//    }
//}
