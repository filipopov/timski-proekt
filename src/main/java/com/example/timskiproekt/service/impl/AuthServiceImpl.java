package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.Role;
import com.example.timskiproekt.domain.exceptions.BadCredentialsException;
import com.example.timskiproekt.domain.exceptions.PasswordsDoNotMatchException;
import com.example.timskiproekt.domain.exceptions.UserAlreadyExistException;
import com.example.timskiproekt.domain.exceptions.UserNotFoundException;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new BadCredentialsException();
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String encodedPassword = user.getPassword();
        
        return this.userRepository.findByUsernameAndPassword(username, encodedPassword)
                .orElseThrow(BadCredentialsException::new);
    }

    @Override
    public void register(String firstName, String lastName, String username, String email, String password, String repeatPassword, String phoneNumber, String address) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() || firstName==null
                || firstName.isEmpty() || lastName==null || lastName.isEmpty())
            throw new BadCredentialsException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UserAlreadyExistException();

        User user = new User(firstName, lastName, username, email, passwordEncoder.encode(password), phoneNumber, address);
        this.userRepository.save(user);
    }
}
