package VApp.VApp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BankException extends Exception{
    private HttpStatus status;
    public BankException(String message,HttpStatus status){
        super(message);
        this.status = status;
    }
}
