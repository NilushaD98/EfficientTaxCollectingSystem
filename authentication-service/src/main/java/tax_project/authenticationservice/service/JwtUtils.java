package tax_project.authenticationservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtils {

    private String secret = "MiAVzqUXy5Tfr1kVIGpPMiAVzqUXy5Tfr1kVIGpP";
    private String expiration = "86400";
    private Key key;

    @PostConstruct
    public void initKey(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }
    public Date getExpirationDate(String token){
        return getClaims(token).getExpiration();
    }
    public String generate(String username,String role, String tokenType){
        Map<String,String> claims = Map.of("username",username,"role",role);
        long expMillis = "ACCESS".equalsIgnoreCase(tokenType)
                ? Long.parseLong(expiration) *1000
                : Long.parseLong(expiration) *1000*5;
        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("username"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }
    private boolean isExpired(String token){
        return getExpirationDate(token).before(new Date());
    }
}
