package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
@EnableEurekaClient
public class SimpleController extends SpringBootServletInitializer {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        String message = "";
        RestTemplate template = new RestTemplate();
        String result = template.getForObject("http://eureka-server.springopenshift.svc:8080", String.class);
        return result;
    }

    @RequestMapping("hello")
    @ResponseBody
    String hello() {
        return "Hello !";
    }


    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
