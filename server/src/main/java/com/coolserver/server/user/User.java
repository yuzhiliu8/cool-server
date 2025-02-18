package com.coolserver.server.user;

import com.coolserver.server.role.RoleType;
import com.coolserver.server.util.Util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
        
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String firstName;
    private String lastName;

    @Column(unique=true)
    private String email;

    private String password;
    private String salt = Util.generateSalt();
    
    public User(){
    }

    public User(RoleType roleType, String firstName, String lastName, String email, String password){
        this.roleType = roleType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = Util.getHashedPassword(password, salt);
    }

    public User(Long id, RoleType roleType, String firstName, String lastName, String email, String password){
        this.roleType = roleType;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = Util.getHashedPassword(password, salt);
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public RoleType getRoleType(){
        return roleType;
    }

    public void setRoleType(RoleType roleType){
        this.roleType = roleType;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
       this.firstName = firstName; 
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = Util.getHashedPassword(password, salt);
    }

    public String getSalt(){
        return salt;
    }

    public String toString(){
       return String.format("User{id = %s email = %s firstName = %s lastName = %s}", id, email, firstName, lastName);
    }


}
