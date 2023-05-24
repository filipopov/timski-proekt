package com.example.timskiproekt.controller;


import com.example.timskiproekt.domain.exceptions.BadCredentialsException;
import com.example.timskiproekt.domain.exceptions.PasswordsDoNotMatchException;
import com.example.timskiproekt.domain.exceptions.UserAlreadyExistException;
import com.example.timskiproekt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "newLogin";
    }

    @PostMapping("/register")
    public String register (@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String username,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String repeatPassword,
                            @RequestParam String phoneNumber,
                            @RequestParam String address) {
        try {
            this.authService.register(firstName, lastName, username, email, password, repeatPassword, phoneNumber, address);
            return "redirect:/login";
        }
        catch (BadCredentialsException | PasswordsDoNotMatchException | UserAlreadyExistException exception){
            return "redirect:/register?error="+exception.getMessage();
        }
    }
}
