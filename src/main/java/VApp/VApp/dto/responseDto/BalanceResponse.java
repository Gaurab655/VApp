package VApp.VApp.dto.responseDto;

public class BalanceResponse {
    private String message;
    private double balance;

    public BalanceResponse(String message,double balance){
        this.balance=balance;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
