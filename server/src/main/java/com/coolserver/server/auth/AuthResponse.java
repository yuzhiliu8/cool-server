package com.coolserver.server.auth;

import com.coolserver.server.session.Session;

public class AuthResponse {
    
    private boolean success;
    private String message;
    private Session session;

    

    public AuthResponse(boolean success, String message, Session session) {
        this.success = success;
        this.message = message;
        this.session = session;
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

    public Session getSession(){
        return session;
    }

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public String toString() {
        return "AuthResponse [success=" + success + ", message=" + message + ", Session=" + session
                + "]";
    }
}
