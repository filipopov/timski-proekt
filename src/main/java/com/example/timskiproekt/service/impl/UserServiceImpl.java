package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.dto.UserDto;
import com.example.timskiproekt.domain.exceptions.InvalidArgumentsException;
import com.example.timskiproekt.domain.exceptions.UserNotFoundException;
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
    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }


    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(RuntimeException::new);
    }


    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
