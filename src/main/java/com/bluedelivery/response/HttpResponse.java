package com.bluedelivery.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse<T> {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";

    private String result;
    private String message;
    private T data;
    
    public HttpResponse(String result) {
        this.result = result;
    }
    
    public HttpResponse(T data) {
        this.data = data;
    }
    
    public HttpResponse(String result, T data) {
        this.result = result;
        this.data = data;
    }

    public HttpResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public static <T> HttpResponse response(String status, T data) {
        return new HttpResponse(status, data);
    }

    public static HttpResponse response(String status, String message) {
        return new HttpResponse(status, message);
    }
    
    public static <T> HttpResponse response(T data) {
        return new HttpResponse(SUCCESS, data);
    }
    
    public static HttpResponse response(String message) {
        return new HttpResponse(SUCCESS, message);
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
