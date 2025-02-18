package com.coolserver.server.user;

import com.coolserver.server.role.RoleType;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String email; 
    private RoleType roleType;

    private UserDTO(){

    }

    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleType(user.getRoleType());

        return userDTO;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", roleType="
                + roleType + "]";
    }
}
