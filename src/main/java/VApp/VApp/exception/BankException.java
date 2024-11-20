package VApp.VApp.exception;

public class BankException extends RuntimeException{
    public String message;
    public String code;
    public BankException(){
        super();
    }
    public BankException(String message, String code){
        super(message);
        this.code = code;
    }
}
