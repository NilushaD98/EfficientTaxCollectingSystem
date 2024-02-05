package taxproject.taxpayerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "The customer Not in database")
public class PersonNotInDatabaseException extends RuntimeException {
}
