package com.coolserver.server.auth;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_requests")
public class AuthRequest{

    @Id
    @SequenceGenerator(name = "auth_generator", sequenceName = "auth_generator", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "auth_generator")
    private Long id;

    private String email;
    private String hash;
    private LocalDate time;
    private boolean success;


    public AuthRequest(){
    }

    public AuthRequest(String email, String hash, LocalDate time, boolean success){
        this.email = email;
        this.hash = hash;
        this.time = time;
        this.success = success;
    }


    public AuthRequest(Long id, String email, String hash, LocalDate time, boolean success){
        this.id = id;
        this.email = email;
        this.hash = hash;
        this.time = time;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "AuthRequest [id=" + id + ", email=" + email + ", hash=" + hash + ", time=" + time + ", success="
                + success + "]";
    }
}

