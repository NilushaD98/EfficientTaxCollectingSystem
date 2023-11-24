package taxproject.apigateway.config;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import taxproject.apigateway.exceptions.JwtTokenMalformedException;
import taxproject.apigateway.exceptions.JwtTokenMissingException;
import taxproject.apigateway.service.JWTUtils;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Component
@RefreshScope
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouteValidator validator;
    @Autowired
    private JWTUtils jwtUtils;
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "YourSecretKey123";

    private boolean authMissing(ServerHttpRequest request){
        return !request.getHeaders().containsKey("Authorization");
    }
    private Mono<Void> onError(ServerWebExchange exchange,HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if(validator.isSecured.test(request)){
            if (authMissing(request)){
                return onError(exchange,HttpStatus.UNAUTHORIZED);
            }

            final String token = decrypt(request.getHeaders().getOrEmpty("Authorization").get(0));

            try {
                jwtUtils.validateToken(token);
                Claims claims = jwtUtils.getClaims(token);
                String requsetedPath = request.getPath().toString();
                log.info("{}",requsetedPath);
                String userRole = claims.get("role",String.class);
                log.info("{}",userRole);
                if(requsetedPath.startsWith("/admin/")&& "ADMIN".equals(userRole)){
                    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
                    return chain.filter(exchange);
                } else if (requsetedPath.startsWith("/users/")&& "USER".equals(userRole)){
                    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
                    return chain.filter(exchange);
                } else if (requsetedPath.startsWith("/tax_payer/")&& "USER".equals(userRole)){
                    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
                    return chain.filter(exchange);
                } else if (requsetedPath.startsWith("/tax_credit/")&& "USER".equals(userRole)){
                    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
                    return chain.filter(exchange);
                }else {
                    log.info("unauthorized");
                    return onError(exchange,HttpStatus.UNAUTHORIZED);
                }
            } catch (JwtTokenMalformedException e) {
                throw new RuntimeException(e);
            } catch (JwtTokenMissingException e) {
                throw new RuntimeException(e);
            }
        }
        return chain.filter(exchange);
    }
    private static String decrypt(String ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

            // Decode Base64
            byte[] combined = Base64.getDecoder().decode(ciphertext);

            // Extract IV and encrypted data
            byte[] iv = new byte[16]; // Assuming 16 bytes IV for AES
            System.arraycopy(combined, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            byte[] encryptedBytes = new byte[combined.length - iv.length];
            System.arraycopy(combined, iv.length, encryptedBytes, 0, encryptedBytes.length);

            // Decrypt the ciphertext
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
