package com.webflux.codestates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerExample(Hello hello) {
        return RouterFunctions.route().path("/hello", builder -> builder.GET("{name}", request -> hello.flux(request))
        ).build();
    }
}
