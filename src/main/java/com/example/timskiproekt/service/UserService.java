package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User register(String email, String password, String repeatPassword, String firstName,
                  String lastName, String phoneNumber, Role role);
}
