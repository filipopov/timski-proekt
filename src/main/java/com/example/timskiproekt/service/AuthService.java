package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.Role;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthService {

    User login(String username, String password);

    void register(@RequestParam String firstName,
                  @RequestParam String lastName,
                  @RequestParam String username,
                  @RequestParam String email,
                  @RequestParam String password,
                  @RequestParam String repeatPassword,
                  @RequestParam String phoneNumber,
                  @RequestParam String address);
}
