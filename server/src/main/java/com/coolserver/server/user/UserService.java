package com.coolserver.server.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
   
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new IllegalArgumentException("No User found with id: " + id);
        }

        return user.get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("No User found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser){
        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("No User found with id: " + id);
        }
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }


}
