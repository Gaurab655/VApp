package VApp.VApp.exception;

import VApp.VApp.entity.User;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User not found ");
    }
}
