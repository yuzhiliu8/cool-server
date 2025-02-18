package com.coolserver.server.user;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolserver.server.auth.APIResponse;
import com.coolserver.server.auth.AuthService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cool-server/api/users")
public class UserController {

    private UserService userService;
    private AuthService authService;

    public UserController(UserService userService, AuthService authService){
        this.userService = userService;
        this.authService = authService; 
    }
    
    //get all users
    @GetMapping("/get-all")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUsers(@CookieValue(value = "sessionId", defaultValue = "-1") String cookie){

        boolean validate = authService.validateSession(Long.parseLong(cookie));
        if (cookie.equals("-1") || validate == false){
            APIResponse<List<UserDTO>> apiResponse = new APIResponse<List<UserDTO>>(false, "401 session unauthorized", null);
            return ResponseEntity.status(401).body(apiResponse);
        }
        List<User> users = userService.getUsers();
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (int i = 0; i < users.size(); i++){
            userDTOs.add(UserDTO.fromUser(users.get(i)));
        }
        APIResponse<List<UserDTO>> apiResponse = new APIResponse<List<UserDTO>>(true, "200 OK", userDTOs);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<APIResponse<UserDTO>> getUserById(
        @PathVariable Long id, 
        @CookieValue(value = "sessionId", defaultValue = "-1") String cookie){

        boolean validate = authService.validateSession(Long.parseLong(cookie));
        if (cookie.equals("-1") || validate == false){
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(false, "401 session unauthorized", null);
            return ResponseEntity.status(401).body(apiResponse);
        }

        try{
            User user = userService.getUserById(id);
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(true, "200 OK", UserDTO.fromUser(user));
            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException e){
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(false, "404 User does not exist", null);
            return ResponseEntity.status(404).body(apiResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse<UserDTO>> createUser(
        @RequestBody User user,
        @CookieValue(value = "sessionId", defaultValue = "-1") String cookie){

        boolean validate = authService.validateSession(Long.parseLong(cookie));
        if (cookie.equals("-1") || validate == false){
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(false, "401 session unauthorized", null);
            return ResponseEntity.status(401).body(apiResponse);
        }

        try {
            User savedUser = userService.createUser(user);
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(true, "200 OK", UserDTO.fromUser(savedUser));
            return ResponseEntity.ok(apiResponse);

        } catch (DataAccessException e){
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(false, "404 User not unique", null);
            return ResponseEntity.status(404).body(apiResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteUser(
        @PathVariable Long id,
        @CookieValue(value = "sessionId", defaultValue = "-1") String cookie){

        boolean validate = authService.validateSession(Long.parseLong(cookie));
        if (cookie.equals("-1") || validate == false){
            APIResponse<String> apiResponse = new APIResponse<String>(false, "401 session unauthorized", null);
            return ResponseEntity.status(401).body(apiResponse);
        }
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new APIResponse<String>(false, "200 OK User successfully deleted", ""));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(404).body(new APIResponse<String>(false, "404 User does not exist", null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(
        @PathVariable Long id, 
        @RequestBody User updatedUser,
        @CookieValue(value = "sessionId", defaultValue = "-1") String cookie){


        boolean validate = authService.validateSession(Long.parseLong(cookie));
        if (cookie.equals("-1") || validate == false){
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(false, "401 session unauthorized", null);
            return ResponseEntity.status(401).body(apiResponse);
        }

        try {
            User user = userService.updateUser(id, updatedUser);
            APIResponse<UserDTO> apiResponse = new APIResponse<UserDTO>(true, "200 OK User successfully updated", UserDTO.fromUser(user));
            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(404).body(new APIResponse<UserDTO>(false, "404 User does not exist", null));
        }
    }
}
