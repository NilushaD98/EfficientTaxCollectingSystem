package tax_project.authenticationservice.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import tax_project.authenticationservice.dto.request.AuthRequest;
import tax_project.authenticationservice.dto.response.AuthResponse;
import tax_project.authenticationservice.dto.response.ResponseUserAuthDetailsDTO;
import tax_project.authenticationservice.proxy.UserProxy;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private final UserProxy userProxy;

    public AuthResponse authenticate(AuthRequest authRequest){

        ResponseUserAuthDetailsDTO userByUserName = userProxy.getUserByUserName(authRequest.getUsername());
        boolean checkpw = BCrypt.checkpw(authRequest.getPassword(), userByUserName.getPassword());

        if (checkpw){
            String access_token = jwtUtils.generate(userByUserName.getUsername(),userByUserName.getJob_title());
            String refresh_token = jwtUtils.generate(userByUserName.getUsername(),userByUserName.getJob_title());

            return new AuthResponse(access_token,refresh_token);
        }
        else {
            return null;
        }
    }

}
