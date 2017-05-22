package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@SpringBootApplication
@EnableEurekaClient
public class SimpleController extends SpringBootServletInitializer {


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello world!";
    }

    @RequestMapping("hello")
    @ResponseBody
    String hello() {
        StringBuilder sb = new StringBuilder();
        List<ServiceInstance> instances = this.discoveryClient.getInstances("Artemis");
        for (ServiceInstance instance : instances) {
            sb.append("Host: ").append(instance.getHost()).append("<br \\>");
            sb.append("Port: ").append(instance.getPort()).append("<br \\>");
            sb.append("ServiceId: ").append(instance.getServiceId()).append("<br \\>");
            sb.append("Uri: ").append(instance.getUri()).append("<br \\>");
            sb.append("Metadata: ").append(instance.getMetadata()).append("<br \\>");
            sb.append("<br \\>");
            sb.append("__________________________________");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
