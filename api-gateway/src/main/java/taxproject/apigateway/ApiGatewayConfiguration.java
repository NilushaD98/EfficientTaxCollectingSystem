package taxproject.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p-> p.path("/user-register/**")
                        .uri("lb://user-service"))
                .route(p->p
                        .path("/fsd-new")
                        .filters(f -> f.rewritePath(
                                "/fsd-new",
                                "/fsd"
                        ))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
