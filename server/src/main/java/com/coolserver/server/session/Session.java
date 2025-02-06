package com.coolserver.server.session;

import java.time.LocalTime;

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
    private LocalTime expireDate;

    public Session(Long sessionId, Long userId){
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Session(Long userId){
        this.userId = userId;
    }

    public Long getSessionId(){
        return sessionId;
    }

    public Long getUserId(){
        return userId;
    }
}

