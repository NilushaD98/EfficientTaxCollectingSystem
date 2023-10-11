package taxproject.apigateway.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import taxproject.apigateway.AuthDetailsProxy;
import taxproject.apigateway.dto.ResponseUserAuthDetailsDTO;


@RestController
@RequiredArgsConstructor
public class UserAuthenticationController {

    private final AuthDetailsProxy authDetailsProxy;

    @GetMapping("/none/{username}")
    public void just(@PathVariable(name = "username")String username){
        System.out.println(username);
        ResponseUserAuthDetailsDTO responseUserAuthDetailsDTO = authDetailsProxy.getUserByUserName(username);
    }
    @GetMapping("/fsd")
    public void sdfsd(){
        String nake = authDetailsProxy.fsdf();
        System.out.println(nake);
    }
    @GetMapping("/get")
    public String sdfds(){
        return "test";
    }
}
