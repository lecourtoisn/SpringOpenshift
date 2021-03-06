package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "Beowulf", configuration = BeowulfConfiguration.class)
public class App extends SpringBootServletInitializer {


    @Autowired
    private DiscoveryClient discoveryClient;

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        StringBuilder sb = new StringBuilder();
        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        sb.append(hostname).append("<br \\>");
        sb.append("<br \\>");
        String beowulfStr = this.restTemplate.getForObject("http://Beowulf/", String.class);
        sb.append("Message from Beowulf: ").append(beowulfStr).append("<br \\>");
        return sb.toString();
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
            sb.append("__________________________________").append("<br \\>");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
