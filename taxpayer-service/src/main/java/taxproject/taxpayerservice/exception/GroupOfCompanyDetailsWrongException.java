package taxproject.taxpayerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "the group of company data cannot acceptable")
public class GroupOfCompanyDetailsWrongException extends RuntimeException{
}
