package com.example.proyectospring.Controller;


import com.example.proyectospring.Models.User;
import com.example.proyectospring.Repositories.UserRepository;
import com.example.proyectospring.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    public ResponseEntity<Object> show(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}/")
    @PreAuthorize("hasRole('SCOPE_ADMIN')")
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
        newUser.setAdmin(user.getAdmin());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/user/image/{email}/")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody Map<String, String> requestBody){
        String imageid = requestBody.get("imageid");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setImageid(imageid);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/user/update/{email}/")
    public ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        // Verificar si el usuario existe en la base de datos

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los datos del usuario con los valores del DTO
        User user = optionalUser.get();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAdmin(userDto.getAdmin());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        // Actualizar la lista de pedidos (orders) si es necesario

        // Guardar el usuario actualizado en la base de datos
        User updatedUser = userRepository.save(user);

        // Crear y devolver el DTO actualizado
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setName(updatedUser.getName());
        updatedUserDto.setUsername(updatedUser.getUsername());
        updatedUserDto.setEmail(updatedUser.getEmail());
        updatedUserDto.setAdmin(updatedUser.getAdmin());
        updatedUserDto.setPassword(updatedUser.getPassword());
        // Actualizar la lista de pedidos (orders) si es necesario

        return ResponseEntity.ok(updatedUserDto);
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
        return ResponseEntity.status(HttpStatus.OK).body("Login successful");
    }
}
