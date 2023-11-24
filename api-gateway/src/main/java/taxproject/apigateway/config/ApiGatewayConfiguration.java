package taxproject.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class ApiGatewayConfiguration {
    @Autowired
    private AuthenticationFilter filter;
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route("user-service",p-> p.path("/users/**")
                        .filters(f -> f.filters(filter))
                        .uri("lb://user-service"))
                .route("auth-service",p -> p.path("/auth/**")
                        .filters(f-> f.filters(filter))
                        .uri("lb://auth-service"))
                .route("auth-service",p -> p.path("/tax_payer/**")
                    .filters(f-> f.filters(filter))
                    .uri("lb://taxpayer-service"))
                .route("auth-service",p -> p.path("/tax_credit/**")
                    .filters(f-> f.filters(filter))
                    .uri("lb://tax-paying-service"))
                .route("my-express-app",p-> p.path("/express/**")
                        .filters(f-> f.filters(filter))
                        .uri("lb://my-express-app"))
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
