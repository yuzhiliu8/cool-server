package com.coolserver.server.auth;

public class APIResponse<T> {
    
    private boolean success;
    private String message;
    private T data;

    public static <T> APIResponse<T> UnauthorizedResponse(){
        return new APIResponse<T>(false, "401 unauthorized", null);
    }

    public static <T> APIResponse<T> OK(T data){
        return new APIResponse<T>(true, "200 OK", data);
    }

    public APIResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData(){
        return data;
    }

    public void setSession(T data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "AuthResponse [success=" + success + ", message=" + message + ", Data" + data
                + "]";
    }
}
