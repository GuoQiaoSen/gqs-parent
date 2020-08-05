package com.gqs.api;

import java.io.Serializable;

/**
 * 响应的全局封装对象
 * 
 * @author lizhibo
 * @since 2018-12-27
 * 
 * @param <T> 响应对象
 */
public final class Response<T> implements Serializable {
    

    public static final int SUCCESS = 200; // 执行成功
    
    public static final int ILLEGAL_REQUEST_PARAMETER = 400; // 请求参数格式不正确
    public static final int LOGIN_REQUIRED = 401; // 需要登录
    public static final int REGISTER_REQUIRED = 402; // 需要注册
    public static final int UN_AUTHORITY = 403; // 没有权限
    
    public static final int CAUGHT_EXCEPTION = 500; // 通用服务端异常
    public static final int FAILURE = 501; // 系统可预见的业务规则的错误

    // 接口特殊的响应 响应码从901开始
    
    private int code;
    
    private T result;
    
    private String message;
    
    public Response() {
    }
    
    private Response(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
    
    public static <T> Response<T> response(int code, String message) {
        return response(code, message, null);
    }
    
    public static <T> Response<T> response(int code, String message, T result) {
        return new Response<T>(code, message, result);
    }
    
    public static <T> Response<T> success() {
        return success("", null);
    }
    
    public static <T> Response<T> success(T result) {
        return success("", result);
    }
    
    public static <T> Response<T> success(String message) {
        return success(message, null);
    }
    
    public static <T> Response<T> success(String message, T result) {
        return response(SUCCESS, message, result);
    }
    
    public static <T> Response<T> illegalRequestParameter(String message) {
        return response(ILLEGAL_REQUEST_PARAMETER, message, null);
    }
    
    public static <T> Response<T> loginRequired() {
        return response(LOGIN_REQUIRED, "您的账号安全保护已失效，再次开启请重新登录！", null);
    }
    
    public static <T> Response<T> registerRequired() {
        return response(REGISTER_REQUIRED, "请注册", null);
    }
    
    public static <T> Response<T> unauthencated() {
        return response(UN_AUTHORITY, "对不起，您没有访问权限", null);
    }
    
    public static <T> Response<T> caughtException() {
        return caughtException("抱歉, 请稍后再试");
    }
    
    public static <T> Response<T> caughtException(String message) {
        return response(CAUGHT_EXCEPTION, message, null);
    }
    
    public static <T> Response<T> failure(String message) {
        return response(FAILURE, message, null);
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.code == SUCCESS;
    }
}

