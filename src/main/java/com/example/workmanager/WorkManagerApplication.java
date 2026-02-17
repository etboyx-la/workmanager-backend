package com.example.workmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String employeeId = request.get("employeeId");
        String password = request.get("password");
        
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
            
            return response;
        }
        
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", "用户名或密码错误");
        
        return error;
    }
}