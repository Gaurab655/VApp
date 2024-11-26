package VApp.VApp.services;

import VApp.VApp.dto.LoginUser;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServices {
    private final Map<String, User> loggedeUsers = new HashMap<>();

    public Map<String, User> getLoggedUsers() {
        return loggedeUsers;
    }
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<User> getUsers() {
        return userRepository.findAll();

    }

    public ResponseEntity<User> findById(Integer id) {
        Optional<User> userEntity = userRepository.findById(id);
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

    public ResponseEntity<User> updateById(User user, Integer id) {
        Optional<User> existingEntity = userRepository.findById(id);
        if (existingEntity.isPresent()) {
            existingEntity.get().setEmail(user.getEmail());
            existingEntity.get().setPassword(user.getPassword());
            User user1 = userRepository.save(existingEntity.get());
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> login(LoginUser loginUser) {
        try {
            Optional<User> userCheck = userRepository.findByEmail(loginUser.getEmail());
            if (userCheck.isEmpty()) {
                System.out.println("Email not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            User user = userCheck.get();
            if (userCheck.get().getPassword().equals(loginUser.getPassword())){
                System.out.println("user found");

                String token = UUID.randomUUID().toString();
                loggedeUsers.put(token, userCheck.get());

                return new ResponseEntity<>(token,HttpStatus.FOUND);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
