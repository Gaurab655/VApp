//package VApp.VApp.service;
//
//import VApp.VApp.entity.User;
//import VApp.VApp.exception.BankException;
//import VApp.VApp.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AdminServices {
//    private final UserRepository userRepository;
//
//    public ResponseEntity<String> insertServiceCharge() throws BankException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User admin = userRepository.findByEmail(email)
//                .orElseThrow(()->new BankException("No admin found", HttpStatus.NOT_FOUND));
//
//
//    }
//}
