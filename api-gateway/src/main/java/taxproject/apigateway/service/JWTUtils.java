package taxproject.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
@Slf4j
@Service
public class JWTUtils {

    private String secret = "MiAVzqUXy5Tfr1kVIGpPMiAVzqUXy5Tfr1kVIGpP";
    private Key key;
    @PostConstruct
    public void initKey(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }
    public boolean isExpired(String token){
        try {
            return getClaims(token).getExpiration().before(new Date());
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

}
