package taxproject.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "user current password doesn't match")
public class UserCurrentPasswordDoesntMatchException extends RuntimeException{
}
