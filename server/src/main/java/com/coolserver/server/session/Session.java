package com.coolserver.server.session;

import java.time.LocalDateTime;

import com.coolserver.server.global.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Session {
    // private static final LocalTime sessionDuration = LocalTime.

    @Id
    @SequenceGenerator(name="session_generator", sequenceName = "session_generator", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="session_generator")
    private Long sessionId;
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    public Session(){

    }
    public Session(Long sessionId, Long userId){
        this.sessionId = sessionId;
        this.userId = userId;
        this.expireDate = LocalDateTime.now().plusDays(AppConstants.SESSION_LENGTH);
    }

    public Session(Long userId){
        this.userId = userId;
        this.expireDate = LocalDateTime.now().plusDays(AppConstants.SESSION_LENGTH);
    }

    public Long getSessionId(){
        return sessionId;
    }

    public Long getUserId(){
        return userId;
    }

    public LocalDateTime getExpireDate(){
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate){
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Session [sessionId=" + sessionId + ", userId=" + userId + ", expireDate=" + expireDate + "]";
    }

}

