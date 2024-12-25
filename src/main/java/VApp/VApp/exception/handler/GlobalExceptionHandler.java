//package VApp.VApp.exception.handler;
//import VApp.VApp.exception.BankException;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(BankException.class)
//    public ResponseEntity<?> handleUserNotFoundException(BankException e) {
//        return new ResponseEntity<>(e.getMessage(), e.getStatus());
//    }
//}
