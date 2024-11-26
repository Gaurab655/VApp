package VApp.VApp.services;

import VApp.VApp.dto.DebitCreditDto;
import VApp.VApp.controller.UserController;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManager {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userServices;

    public String debit(String token,DebitCreditDto debitCreditDto){
        User loggedUser =userServices.getLoggedUsers().get(token);
        if (loggedUser==null){
            return "Unauthorized user or invalid token";
        }
        Account account= loggedUser.getAccountEntity();
        if (account==null){
            return "account not found";
        }
        if (account.getPin()!=debitCreditDto.getPin()){
            return "Pin is incorrect ";
        }
        double updateBalane=account.getBalance()+debitCreditDto.getBalance();
        account.setBalance(updateBalane);
        accountRepository.save(account);

        return "account debited successfully" +updateBalane;

    }
}
