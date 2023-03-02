package com.atguigu.spring_security.domain.response;

public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static <T> Response.success success(T result){
        return new success<>(result);
    }
    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }
    public static class success<T> {
        private int code;
        private T result;

        public success(T result) {
            this.code = 200;
            this.result = result;
        }
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
