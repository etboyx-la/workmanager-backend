package com.example.workmanager.controller;

import com.example.workmanager.model.ApiResult;
import com.example.workmanager.model.LoginRequest;
import com.example.workmanager.model.LoginResponse;
import com.example.workmanager.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostMapping("/login")
    public ResponseEntity<ApiResult<LoginResponse>> login(@RequestBody LoginRequest request) {
        // 简单的登录验证 (开发环境)
        if ("demo".equals(request.getEmployeeId()) && "123456".equals(request.getPassword())) {
            User user = new User();
            user.setEmployeeId("demo");
            user.setName("测试用户");
            user.setDepartment("技术部");
            user.setRole("user");
            
            // 生成JWT token
            String token = generateToken(user);
            
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setMessage("登录成功");
            response.setData(user);
            
            // 将token添加到响应头
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(ApiResult.success("登录成功", response));
        }
        
        return ResponseEntity.ok(ApiResult.error("用户名或密码错误"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResult<Void>> logout(@RequestHeader("Authorization") String token) {
        // 在实际应用中，这里应该将token加入黑名单
        return ResponseEntity.ok(ApiResult.success("退出成功", null));
    }

    private String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("employeeId", user.getEmployeeId());
        claims.put("role", user.getRole());
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmployeeId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24小时
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}