package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.dto.UserDto;
import com.example.timskiproekt.domain.enumerations.Role;
import com.example.timskiproekt.domain.exceptions.InvalidArgumentsException;
import com.example.timskiproekt.domain.exceptions.PasswordsDoNotMatchException;
import com.example.timskiproekt.domain.exceptions.UserAlreadyExistException;
import com.example.timskiproekt.domain.exceptions.UserNotFoundException;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User register(String email, String password, String repeatPassword, String firstName,
                         String lastName, String phoneNumber, Role role) {
        if (email.isEmpty() || email.isBlank() || password.isBlank() || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistException();

        User user = new User(firstName, lastName, email, passwordEncoder.encode(password), phoneNumber);
        return userRepository.save(user);
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
}
