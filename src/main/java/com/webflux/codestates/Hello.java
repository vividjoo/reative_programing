package com.webflux.codestates;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

@Component
public class Hello {

    public ServerResponse flux(ServerRequest req) {
        String name = req.pathVariable("name");

        System.out.println("name : " + name);
        Map<String, String> map = new HashMap<>();
        map.put("message", "hello " + name);
        map.put("to", name);
        JSONObject json = new JSONObject(map);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(json.toMap());
    }
}