package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, Model model){

        User user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
        request.getSession().setAttribute("user", user);
        model.addAttribute("user", user);
        return ResponseEntity.ok().body("success");

    }
}
