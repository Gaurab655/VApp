package VApp.VApp.services;

import VApp.VApp.controller.UserController;
import VApp.VApp.dto.AccountManagerDto;
import VApp.VApp.dto.LoginUser;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManager {
    private AccountRepository accountRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;



}
