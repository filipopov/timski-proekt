package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
