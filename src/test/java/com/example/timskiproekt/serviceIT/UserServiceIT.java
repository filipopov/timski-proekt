package com.example.timskiproekt.serviceIT;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.exceptions.*;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
    void testFindById_existingId_shouldReturnUser() {
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
    void testFindById_nonExistingId_shouldThrowException() {
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
    void testLoadUserByUsername_existingUsername_shouldReturnUserDetails() {
        // Given
        String username = "test@example.com";
        User user = new User();
        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

        // When
        UserDetails result = userService.loadUserByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(username);
    }

    @Test
    void testLoadUserByUsername_nonExistingUsername_shouldThrowException() {
        // Given
        String username = "nonexisting@example.com";
        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(UserNotFoundException.class, () -> userService.loadUserByUsername(username));
        verify(userRepository, times(1)).findByEmail(username);
    }

    @Test
    void testLogin_validCredentials_shouldReturnUser() {
        // Arrange
        String username = "testuser";
        String password = "password";
        User user = new User();
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(user));

        // Act
        User result = userService.login(username, password);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testLogin_missingUsername_shouldThrowInvalidArgumentsException() {
        // Arrange
        String password = "password";

        // Act and Assert
        assertThrows(InvalidArgumentsException.class, () -> userService.login(null, password));
        assertThrows(InvalidArgumentsException.class, () -> userService.login("", password));
        verifyNoInteractions(userRepository);
    }

    @Test
    void testLogin_missingPassword_shouldThrowInvalidArgumentsException() {
        // Arrange
        String username = "testuser";

        // Act and Assert
        assertThrows(InvalidArgumentsException.class, () -> userService.login(username, null));
        assertThrows(InvalidArgumentsException.class, () -> userService.login(username, ""));
        verifyNoInteractions(userRepository);
    }

    @Test
    void testLogin_invalidCredentials_shouldThrowException() {
        // Arrange
        String username = "testuser";
        String password = "password";
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userService.login(username, password));
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testRegister_validData_shouldReturnRegisteredUser() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String repeatPassword = "password";
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String phoneNumber = "123456789";
        String address = "123 Main St";
        User user = new User();
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.register(email, password, repeatPassword, firstName, lastName, username, phoneNumber, address);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).encode(password);
    }
}
