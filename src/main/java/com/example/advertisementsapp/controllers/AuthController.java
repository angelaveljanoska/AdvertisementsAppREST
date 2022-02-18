package com.example.advertisementsapp.controllers;

import com.example.advertisementsapp.models.User;
import com.example.advertisementsapp.services.UserService;
import com.example.advertisementsapp.utility.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    public UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {return ResponseEntity.ok(userService.getUserById(id));}

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return  ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody AuthDTO authDTO) throws Exception {
        return ResponseEntity.ok(userService.register(authDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthDTO authDTO) throws Exception {
        return ResponseEntity.ok(userService.login(authDTO));
    }

    @PostMapping("/unregister")
    @Transactional
    public ResponseEntity<String> unregister(@RequestBody AuthDTO authDTO) throws Exception {
        return ResponseEntity.ok(userService.unregister(authDTO));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ResponseEntity<String> handleAllExceptions(Exception e, WebRequest wr) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
