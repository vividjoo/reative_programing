package com.webflux.codestates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import java.util.HashMap;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
public class Hello {

//    public Mono<ServerResponse> flux(ServerRequest req) {
//        String name = req.pathVariable("name");
//
//
//        System.out.println("name : " + name);
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "hello " + name);
//        map.put("to", name);
//        JSONObject json = new JSONObject(map);
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(json.toMap());
//    }


    public Mono<ServerResponse> helloHandler (ServerRequest req) {
        System.out.println("123123123123");
        String name = req.queryParam("name").get();
//        String name = req.pathVariable("name");
        HashMap nameMap = new HashMap();
        nameMap.put("to", name);
        nameMap.put("message", "hello "+name);
        Mono<HashMap> mymono = Mono.just(nameMap);
        return
                ServerResponse.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromProducer(mymono, HashMap.class));
    }
}