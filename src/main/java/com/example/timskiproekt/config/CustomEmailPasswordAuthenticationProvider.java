package com.example.timskiproekt.config;

import com.example.timskiproekt.domain.exceptions.BadCredentialsException;
import com.example.timskiproekt.domain.exceptions.InvalidArgumentsException;
import com.example.timskiproekt.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomEmailPasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomEmailPasswordAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (email.isEmpty() || password.isEmpty())
            throw new BadCredentialsException();
        UserDetails userDetails = this.userService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException();

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomEmailPasswordAuthenticationProvider.class);
    }
}
