package com.example.workmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("message", "API测试正常");
        result.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/api/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("appName", "WorkManager Backend");
        info.put("version", "1.0.0");
        info.put("environment", "Railway");
        info.put("javaVersion", System.getProperty("java.version"));
        return ResponseEntity.ok(info);
    }
}