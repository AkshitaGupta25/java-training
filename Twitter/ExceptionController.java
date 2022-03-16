package Twitter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = Twitter.UserNotRegistered.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        return new ResponseEntity<>("User not registered", HttpStatus.NOT_FOUND);
    }
}