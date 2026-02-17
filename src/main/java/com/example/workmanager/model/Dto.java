package com.example.workmanager.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String employeeId;
    private String password;
}

@Data
public class LoginResponse {
    private Boolean success;
    private String message;
    private User data;
}

@Data
public class ApiResult<T> {
    private Boolean success;
    private String message;
    private T data;
    
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(true);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }
    
    public static <T> ApiResult<T> success(String message, T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(true);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    
    public static <T> ApiResult<T> error(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}