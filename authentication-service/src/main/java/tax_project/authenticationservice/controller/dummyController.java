package tax_project.authenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dummyController {

    @GetMapping("/sec2")
    public void sfd(){
        System.out.println("sdfdsfdf");
    }
}
