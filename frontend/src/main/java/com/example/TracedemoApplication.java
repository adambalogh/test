package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class TracedemoApplication {
    private static final Logger LOG = Logger.getLogger(TracedemoApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(TracedemoApplication.class, args);
    }

    @Autowired private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/")
    public String index() {
        int sum = 0;
        for (int i = 0; i < 1000; ++i) {
            sum += i;
        }
        return Integer.toString(sum);
    }

    @RequestMapping("/work")
    public Trace callHome() {
        Trace trace = new Trace();
        trace.add("Frontend at: " + System.currentTimeMillis());
        trace = restTemplate.postForObject("http://localhost:8090/expand", trace, Trace.class);
        trace.add("Frontend at: " + System.currentTimeMillis());
        trace = restTemplate.postForObject("http://localhost:8090/expand", trace, Trace.class);
        return trace;
    }

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
