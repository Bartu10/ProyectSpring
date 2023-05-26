package com.example.proyectospring.Controller;


import com.example.proyectospring.Models.User;
import com.example.proyectospring.Repositories.UserRepository;
import com.example.proyectospring.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}/")
    public  ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }


    @GetMapping("/users/mail/{email}/")
    public ResponseEntity<Object> show(@PathVariable("email") String email){
        return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> userRepository.delete(value));
        return new ResponseEntity<>(user.isPresent(), HttpStatus.OK);
    }

    @PostMapping("/users/create")
    public ResponseEntity<Object> create(@RequestBody UserDto user) {
        User newUser=new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setS(user.getS());
        userRepository.save(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()) {
            user.setId(id);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
/*
    @GetMapping("/users/email/{email}/")
    public ResponseEntity<Object> show(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    */

    @PostMapping(value = "/users/login")
    public ResponseEntity<Object> login(@RequestBody UserDto user) {
        // Check if user exists in database
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Check if password is correct
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // If user exists and password is correct, return a success response
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }
}
