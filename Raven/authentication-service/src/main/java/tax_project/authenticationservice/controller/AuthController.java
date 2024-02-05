package tax_project.authenticationservice.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tax_project.authenticationservice.dto.request.AuthRequest;
import tax_project.authenticationservice.dto.response.AuthResponse;
import tax_project.authenticationservice.service.AuthService;
import org.springframework.web.bind.annotation.RequestHeader;
import tax_project.authenticationservice.util.StandardResponse;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "register")
    public ResponseEntity<StandardResponse> register(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Tokens : ",authService.authenticate(authRequest)), HttpStatus.OK
        );
    }
    @GetMapping(value = "sec")
    public void fsdfd(){
        System.out.println("dfsdf");
    }
}
