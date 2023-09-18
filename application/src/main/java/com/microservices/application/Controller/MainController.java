package com.microservices.application.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@AllArgsConstructor
@Component
public class MainController {

    public MainController() {
        System.out.println("Controller initialized successfully");
    }

    @GetMapping(value = "/getPayments")
    public Object getPayments(){
        System.out.println("Getting payment details");
        RestTemplate template = new RestTemplate();
        ResponseEntity<Object> forEntity = template.getForEntity("http://localhost:8080/allPayments", Object.class);
return forEntity;
    }
}
