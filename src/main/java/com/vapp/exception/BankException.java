package com.vapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BankException extends Exception {
    private HttpStatus status;
    private String message;

    public BankException(String message ,HttpStatus status) {
        super(message);
        this.message = "";
        this.status = status;
    }
    public BankException(String message){
        super(message);
        this.message=message;
        this.status=HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
