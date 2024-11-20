package VApp.VApp.controller;

import VApp.VApp.dto.AccountManagerDto;
import VApp.VApp.dto.LoginUser;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.services.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountManager")
public class AccountManageController {
    @Autowired
    private AccountManager accountManager;

}
