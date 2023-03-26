package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.Role;
import com.example.timskiproekt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/registerUser")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> createUser(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String password,
                                           @RequestParam String phoneNumber,
                                           @RequestParam Role userRole) {
        userService.save(new User(firstName, lastName, email, password, phoneNumber, userRole));
        return ResponseEntity.status(HttpStatus.CREATED).body("User has been created");
    }
}
