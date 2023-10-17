package taxproject.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "The user ID Not in the database ")
public class UserNotFoundException extends RuntimeException{
}
