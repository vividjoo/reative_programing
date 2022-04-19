package com.webflux.codestates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@EnableWebFlux
public class RouterConfig {

//    @Bean
//    public RouterFunction<ServerResponse> routerExample(Hello hello) {
//        return RouterFunctions.route().path("/hello", builder -> builder.GET("{name}", request -> hello.helloHandler(request))
//        ).build();
//
////        return (RouterFunction<ServerResponse>) RouterFunctions.route(
////                RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
////                hello::helloHandler);
//    }

//   아래 설정
    @Bean
    public RouterFunction<ServerResponse> helloRouter(Hello hello) {
        return RouterFunctions.route()
                .GET("/hello", hello::helloHandler)
                .build();
    }
    //   아래 설정
}
