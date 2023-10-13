package taxproject.apigateway.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import taxproject.apigateway.service.JWTUtils;


@Component
@RefreshScope
@Slf4j
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouteValidator validator;
    @Autowired
    private JWTUtils jwtUtils;

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
            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            log.info("token : {}",token);

            if (jwtUtils.isExpired(token)){
                return onError(exchange,HttpStatus.UNAUTHORIZED);
            }

        }
        return chain.filter(exchange);

    }
}
