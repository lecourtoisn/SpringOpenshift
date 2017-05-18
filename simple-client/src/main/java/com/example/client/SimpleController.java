package com.example.client;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${eureka_server}")
    String eurekaServer = "No eureka server found";

    @Value("${welcome_message}")
    String welcomeMessage = "No welcome message found";

    @Value("${not_existing_config}")
    String notExistingConfig = "No such thing";

    @RequestMapping("/")
    @ResponseBody
    String home() {
        String message = "";
        RestTemplate template = new RestTemplate();
        String result = template.getForObject("http://eureka-server.springopenshift.svc:8080/eureka/apps", String.class);
        return result;
    }

    @RequestMapping("hello")
    @ResponseBody
    String hello() {
        StringBuilder builder = new StringBuilder();
        builder.append(eurekaServer).append("\n");
        builder.append(welcomeMessage).append("\n");
        builder.append(notExistingConfig).append("\n");
        return builder.toString();
    }


    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
