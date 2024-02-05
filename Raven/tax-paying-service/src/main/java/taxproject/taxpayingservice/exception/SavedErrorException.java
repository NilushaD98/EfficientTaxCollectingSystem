package taxproject.taxpayingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "that entered data not valid")
public class SavedErrorException extends RuntimeException {
}
