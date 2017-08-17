package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class TracebackendApplication {
    private static final Logger LOG = Logger.getLogger(TracebackendApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(TracebackendApplication.class, args);
    }

    @RequestMapping(value = "/expand", method = RequestMethod.POST)
    public Trace home(@RequestBody Trace trace) {
        trace.add("Backend at: " + System.currentTimeMillis());
        return trace;
    }

    @Autowired private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
