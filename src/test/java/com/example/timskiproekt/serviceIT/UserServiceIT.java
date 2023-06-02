package com.example.timskiproekt.serviceIT;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.exceptions.UserNotFoundException;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceIT {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Given
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User result = userService.save(user);

        // Then
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByIdExistingIdShouldReturnUser() {
        // Given
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        User result = userService.findById(userId);

        // Then
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testFindByIdNonExistingIdShouldThrowException() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(RuntimeException.class, () -> userService.findById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testFindAll() {
        // Given
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(users.size(), result.size());
        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testLoadUserByUsernameExistingUsernameShouldReturnUser() {
        // Given
        String username = "test@example.com";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // When
        User result = userService.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsernameNonExistingUsernameShouldThrowException() {
        // Given
        String username = "nonexisting@example.com";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(UserNotFoundException.class, () -> userService.findByUsername(username));
        verify(userRepository, times(1)).findByUsername(username);
    }
}
