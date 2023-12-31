package tax_project.authenticationservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tax_project.authenticationservice.dto.response.ResponseUserAuthDetailsDTO;

@FeignClient(name = "user-service")
public interface UserProxy {

    @GetMapping(path = "/users/findUserByUserName/{username}/{walletaddress}")
    public ResponseUserAuthDetailsDTO getUserByUserName(
            @PathVariable(name = "username") String username,
            @PathVariable(name = "walletaddress") String walletAddress
    );
}
