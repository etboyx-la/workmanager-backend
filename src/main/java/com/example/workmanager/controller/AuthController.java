package com.example.workmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
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
            
            return ResponseEntity.ok(response);
        }
        
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", "用户名或密码错误");
        
        return ResponseEntity.ok(error);
    }
}