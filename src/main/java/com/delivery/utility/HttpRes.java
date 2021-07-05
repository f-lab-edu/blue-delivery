package com.delivery.utility;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpRes<T> {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";


    private String status;
    private String message;
    private T data;

    public HttpRes(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public HttpRes(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> HttpRes res(String status, T data) {
        return new HttpRes(status, data);
    }

    public static HttpRes res(String status, String message) {
        return new HttpRes(status, message);
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
