package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(User user);

    List<User> saveAll(List<User> users);

    User findById(Long id);

    List<User> findAll();

    User register(String email, String password, String repeatPassword, String firstName,
                  String lastName, String username, String phoneNumber, String address);

    User login(String username, String password);

    void updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    void deleteAll();
}
