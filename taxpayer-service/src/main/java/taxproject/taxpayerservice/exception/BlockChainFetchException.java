package taxproject.taxpayerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS,reason = "blockchain fetch exception")
public class BlockChainFetchException extends RuntimeException {
}
