package com.example.advertisementsapp.services;

import com.example.advertisementsapp.models.Role;
import com.example.advertisementsapp.models.User;
import com.example.advertisementsapp.repositories.UserRepository;
import com.example.advertisementsapp.utility.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) { return userRepository.findById(id).get(); }

    public User register(AuthDTO authDTO) throws Exception {
        Optional<User> existingUser = userRepository.findUserByUsername(authDTO.username);
        if (existingUser.isPresent()) {
            throw new Exception("User already exists!");
        }
        if(authDTO.password.equals(authDTO.secondPassword)) {
            User user = User.from(authDTO);
            return userRepository.save(user);
        }
        else {
            throw new Exception("Passwords don't match!");
        }
    }

    public User login(AuthDTO authDTO) throws Exception {
        User user = userRepository.findUserByUsername(authDTO.username).orElseThrow();

        if(user.getPassword().equals(authDTO.password)) {
            return user;
        }
        else {
            throw new Exception("Incorrect credentials!");
        }
    }

    public String unregister(AuthDTO authDTO) throws Exception {
        if(authDTO.password.equals(authDTO.secondPassword)) {
            userRepository.deleteUserByUsername(authDTO.username);
            return "User deleted successfully!";
        }
        else {
            throw new Exception("Passwords don't match!");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initAdmin() {
        userRepository.save(new User("admin", "admin", Role.ADMIN, new ArrayList<>()));
    }
}
