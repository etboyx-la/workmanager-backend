package com.example.workmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String employeeId = request.get("employeeId");
        String password = request.get("password");
        
        System.out.println("收到登录请求: " + employeeId);
        
        // 简单的登录验证 (开发环境)
        if ("demo".equals(employeeId) && "123456".equals(password)) {
            Map<String, Object> user = new HashMap<>();
            user.put("employeeId", "demo");
            user.put("name", "测试用户");
            user.put("department", "技术部");
            user.put("role", "user");
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("data", user);
            
            return ResponseEntity.ok(response);
        }
        
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", "用户名或密码错误");
        
        return ResponseEntity.ok(error);
    }

    @GetMapping("/api/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
    
    @GetMapping("/api/test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("message", "API测试正常");
        result.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(result);
    }
}