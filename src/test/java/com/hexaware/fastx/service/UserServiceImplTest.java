package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class UserServiceImplTest {

    @Mock
    private UserRepository repo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPass");
        when(repo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User created = userService.createUser(user);

        assertEquals("encodedPass", created.getPassword());
        assertEquals("test@example.com", created.getEmail());
        verify(repo).save(any(User.class));
    }

    @Test
    void testGetUserById_Found() {
        User user = new User();
        user.setName("Alice");
        when(repo.findById(1)).thenReturn(Optional.of(user));

        User found = userService.getUserById(1);

        assertEquals("Alice", found.getName());
    }

    @Test
    void testGetUserById_NotFound() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99));
    }

    @Test
    void testDeleteUser_Success() {
        User user = new User();
        when(repo.findById(1)).thenReturn(Optional.of(user));
        doNothing().when(repo).delete(user);

        assertDoesNotThrow(() -> userService.deleteUser(1));
        verify(repo).delete(user);
    }
}
