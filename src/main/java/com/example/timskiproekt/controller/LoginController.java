package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {


    private final UserService userService;

    @PostMapping
    public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password){
        User user;
        try{
            user = this.userService.login(username, password);
            request.getSession().setAttribute("user", user);
            return user.getUsername();
        }
        catch (RuntimeException exception){
            return exception.getMessage();
        }
    }
}
