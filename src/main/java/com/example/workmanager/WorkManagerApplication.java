package com.example.workmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController  
public class WorkManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkManagerApplication.class, args);
    }
    
    @GetMapping("/")
    public String home() {
        return "API is running";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}