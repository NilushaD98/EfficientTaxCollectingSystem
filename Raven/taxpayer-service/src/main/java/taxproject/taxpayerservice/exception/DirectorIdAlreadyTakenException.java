package taxproject.taxpayerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED,reason = "Director Already Saved;")
public class DirectorIdAlreadyTakenException extends RuntimeException{
}
