package com.example.timskiproekt.controller;

import com.example.timskiproekt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> signUp(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam String repeatPassword,
                                       @RequestParam String username,
                                       @RequestParam String phoneNumber,
                                       @RequestParam String address) {
        userService.register(email, password, repeatPassword, firstName, lastName, username, phoneNumber, address);
        return ResponseEntity.ok().build();
    }
}
