package com.example.ApiGateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints=List.of(
            "/auth/login","/auth/register", "/api/movies"
    );

    public Predicate<ServerHttpRequest> isSecured=
            request->openApiEndpoints
                    .stream()
                    .noneMatch(uri->request.getURI().getPath().contains(uri));

}
