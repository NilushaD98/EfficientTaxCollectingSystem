package taxproject.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import taxproject.apigateway.AuthDetailsProxy;
//import taxproject.apigateway.dto.UserAuthDataDTO;
//import taxproject.apigateway.util.StandardResponse;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Slf4j
//@Configuration
//public class ApplicationConfig {
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private AuthDetailsProxy authDetailsProxy;
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                ResponseEntity<StandardResponse> userByUserName = authDetailsProxy.getUserByUserName(username);
//                UserAuthDataDTO userAuthDataDTO = (UserAuthDataDTO) userByUserName.getBody().getData();
//                if(userByUserName.getStatusCodeValue() != 200){
//                    log.error("{} user not in the database",username);
//                    throw new UsernameNotFoundException("user not in database");
//                }else {
//                    Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(username);
//                }
//            }
//        }
//    }
//}
