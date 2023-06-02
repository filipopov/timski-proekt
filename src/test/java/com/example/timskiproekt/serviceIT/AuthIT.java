package com.example.timskiproekt.serviceIT;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.exceptions.BadCredentialsException;
import com.example.timskiproekt.domain.exceptions.InvalidArgumentsException;
import com.example.timskiproekt.domain.exceptions.PasswordsDoNotMatchException;
import com.example.timskiproekt.domain.exceptions.UserAlreadyExistException;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.impl.AuthServiceImpl;
import com.example.timskiproekt.service.impl.UserServiceImpl;
import com.example.timskiproekt.utils.BaseTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthIT {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void successfullyRegisteredNewUser() {
        String firstName = "Stefan";
        String lastName = "Stefanovski";
        String username = "stefan";
        String password = "password";
        String phoneNumber = "070123456";
        String address = "Address1";
        User user = BaseTestData.generateRandomUser(false);

        //when
        authService.register(firstName, lastName, username, user.getEmail(), password, "password", phoneNumber, address);

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(user);
    }


    @DisplayName("throw exception when register with invalid firstname")
    @Test
    public void registerWithInvalidFirstname() {

        assertThrows(BadCredentialsException.class, () -> authService.register("", "Petrovski", "petrovski",
                "ppetrovski@gmail.com", "password", "password", "070123456", "address"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("throw exception when register with invalid lastname")
    @Test
    public void registerWithInvalidLastname() {

        assertThrows(BadCredentialsException.class, () -> authService.register("Petar", null, "petrovski",
                "ppetrovski@gmail.com", "password", "password", "070123456", "address"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("throw exception when register with invalid username")
    @Test
    public void registerWithInvalidUsername() {

        assertThrows(BadCredentialsException.class, () -> authService.register("Petar", "Petrovski", "",
                "ppetrovski@gmail.com", "password", "password", "070123456", "address"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("throw exception when register with invalid password")
    @Test
    public void registerWithInvalidPassword() {

        assertThrows(BadCredentialsException.class, () -> authService.register("Petar", "Petrovski", "petrovski",
                "ppetrovski@gmail.com", "", "password", "070123456", "address"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("throw exception when register with passwords do not match")
    @Test
    public void throwPasswordsDoNotMatchException() {

        assertThrows(PasswordsDoNotMatchException.class, () -> authService.register("Petar", "Petrovski", "petrovski",
                "ppetrovski@gmail.com", "password", "pass", "070123456", "address"));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("throw exception when trying to register without address")
    @Test
    public void addressCanNotBeEmpty() {

        assertThrows(InvalidArgumentsException.class, () -> authService.register("Petar", "Petrovski", "petrovski",
                "ppetrovski@gmail.com", "password", "password", "070123456", ""));
        verifyNoInteractions(userRepository);
    }

    @DisplayName("throw exception when trying to register without address")
    @Test
    public void trying() {

        assertThrows(InvalidArgumentsException.class, () -> authService.register("Petar", "Petrovski", "petrovski",
                "ppetrovski@gmail.com", "password", "password", "070123456", ""));
        verifyNoInteractions(userRepository);
    }

    @Test
    public void shouldReturnUserAlreadyExistsWhenTryingToRegister() {
        User user = BaseTestData.generateRandomUser(false);
        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));

        assertThrows(UserAlreadyExistException.class,
                () -> authService.register("Petar", "Petrovski", user.getUsername(),
                        "ppetrovski@gmail.com", "password", "password", "070123456", "address"));
    }

    @Test
    void testLoginValidCredentialsShouldReturnUser() {
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
    void testLoginMissingUsernameShouldThrowInvalidArgumentsException() {
        // Arrange
        String password = "password";

        // Act and Assert
        assertThrows(InvalidArgumentsException.class, () -> userService.login(null, password));
        assertThrows(InvalidArgumentsException.class, () -> userService.login("", password));
        verifyNoInteractions(userRepository);
    }

    @Test
    void testLoginMissingPasswordShouldThrowInvalidArgumentsException() {
        // Arrange
        String username = "testuser";

        // Act and Assert
        assertThrows(InvalidArgumentsException.class, () -> userService.login(username, null));
        assertThrows(InvalidArgumentsException.class, () -> userService.login(username, ""));
        verifyNoInteractions(userRepository);
    }

    @Test
    void testLoginInvalidCredentialsShouldThrowException() {
        // Arrange
        String username = "testuser";
        String password = "password";
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userService.login(username, password));
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

}
