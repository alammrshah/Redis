package com.example.practice.Controller;

import com.example.practice.Entity.User;
import com.example.practice.Repository.UserRepository;
import com.example.practice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private com.example.practice.Repository.UserRepository userRepository;

    @Autowired
    private UserService userService;

    // CREATE - POST
    @PostMapping
    public ResponseEntity<com.example.practice.Entity.User> createUser(@Valid @RequestBody User user) {
        user = userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    // READ - GET all
    @GetMapping
    public List<com.example.practice.Entity.User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ - GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user =  userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE - PUT (full update)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @Valid @RequestBody User updatedUser) {
        return userService.findById(id);
    }

    // PATCH - Partial update
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return userRepository.findById(id).map(user -> {
            updates.forEach((k, v) -> {
                switch (k) {
                    case "firstName" -> user.setFirstName((String) v);
                    case "lastName" -> user.setLastName((String) v);
                    case "phoneNumber" -> user.setPhoneNumber((String) v);
                    case "email" -> user.setEmail((String) v);
                    case "department" -> user.setDepartment((String) v);
                    case "course" -> user.setCourse((String) v);
                    case "birthDate" -> user.setBirthDate(java.time.LocalDate.parse((String) v));
                }
            });
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

