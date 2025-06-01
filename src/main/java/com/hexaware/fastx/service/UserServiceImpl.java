package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.AuthResponseDTO;
import com.hexaware.fastx.dto.LoginRequestDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public String registerUser(@Valid UserDTO userDto) {
        if (repo.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setGender(userDto.getGender());
        user.setContactNumber(userDto.getContactNumber());
        user.setRole(userDto.getRole());

        repo.save(user);
        return "User registered successfully";
    }

    public AuthResponseDTO loginUser(@Valid LoginRequestDTO loginDto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginDto.getEmail());
            return new AuthResponseDTO(token);
        } else {
            throw new RuntimeException("Invalid login credentials");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return repo.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User updateUser(int id, User updatedUser) {
        User u = getUserById(id);
        u.setName(updatedUser.getName());
        u.setEmail(updatedUser.getEmail());
        u.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        u.setGender(updatedUser.getGender());
        u.setContactNumber(updatedUser.getContactNumber());
        u.setRole(updatedUser.getRole());
        return repo.save(u);
    }

    @Override
    public void deleteUser(int id) {
        User u = getUserById(id);
        repo.delete(u);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }
}
