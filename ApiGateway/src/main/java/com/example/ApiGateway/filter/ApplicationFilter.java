package com.example.ApiGateway.filter;

import com.example.ApiGateway.config.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationFilter extends AbstractGatewayFilterFactory<ApplicationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;

    public ApplicationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer")){
                    authHeader = authHeader.substring(7);
                }
                try{
                    jwtService.validateToken(authHeader);
                    if(!routeValidator.IsAuthenticated(exchange.getRequest().getURI().getPath(), jwtService.getRole(authHeader))){
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
                    }
                } catch (Exception e){
                    System.out.println(e);
                    throw new RuntimeException("Unauthorized access");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }

}
