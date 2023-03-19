package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();
}
