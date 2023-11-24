package tax_project.authenticationservice.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tax_project.authenticationservice.dto.request.AuthRequest;
import tax_project.authenticationservice.dto.response.AuthResponse;
import tax_project.authenticationservice.service.AuthService;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest
    , HttpServletRequest request){
        System.out.println(request.getHeader("name"));
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
    @GetMapping(value = "sec")
    public void fsdfd(){
        System.out.println("dfsdf");
    }
}
