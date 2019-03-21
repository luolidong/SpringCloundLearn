package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dell on 2019/3/21.
 */
@RestController
public class FrontendController {
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    private BackendClient backendClient;

//    @Bean
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

//    @GetMapping("/")
//    public String callBackend() {
//        return restTemplate.getForObject("http://localhost:9000/api", String.class);
//    }

    @GetMapping("/client")
    public String callBackend2() {
        return backendClient.printDate();
    }
}
