package com.webflux.codestates;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


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
        Map<String, String> hashMap = new HashMap();
        hashMap.put("to", name);
        hashMap.put("message", "hello "+name);
        Mono<Map> mono = Mono.just(hashMap);
        return
                ServerResponse.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromProducer(mono, HashMap.class));
    }
}