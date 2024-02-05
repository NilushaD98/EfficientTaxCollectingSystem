package taxproject.taxpayingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "that entered data inconsistent")
public class DataInconsistentException extends RuntimeException{
}
