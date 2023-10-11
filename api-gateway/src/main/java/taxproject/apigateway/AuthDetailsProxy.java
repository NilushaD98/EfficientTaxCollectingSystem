package taxproject.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import taxproject.apigateway.dto.ResponseUserAuthDetailsDTO;

@FeignClient(name = "user-service",url = "http://localhost:8000")
public interface AuthDetailsProxy {

    @GetMapping(path = "/user/findUserByUserName/{username}")
    public ResponseUserAuthDetailsDTO getUserByUserName(
            @PathVariable(name = "username") String username
    );

    @GetMapping("/user/test")
    public String fsdf();
}
