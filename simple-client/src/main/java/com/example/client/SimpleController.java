package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@EnableEurekaClient
public class SimpleController extends SpringBootServletInitializer {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello world!";
    }

    @RequestMapping("hello")
    @ResponseBody
    String hello() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(welcomeMessage).append("\n");
//        builder.append(notExistingConfig).append("\n");
//        return builder.toString();
        return "Ok";
    }


    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
