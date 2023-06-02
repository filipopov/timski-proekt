package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> saveAll(List<User> users);

    User findById(Long id);

    List<User> findAll();

    User login(String username, String password);

    void updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    void deleteAll();

    User findByUsername(String username);
}
