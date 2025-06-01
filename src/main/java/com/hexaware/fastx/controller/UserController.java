package com.hexaware.fastx.controller;
import com.hexaware.fastx.config.UserInfoUserDetails;
import com.hexaware.fastx.dto.AuthResponseDTO;
import com.hexaware.fastx.dto.LoginRequestDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;
    

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginDTO) {
        return ResponseEntity.ok(userService.loginUser(loginDTO));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId, Authentication authentication) {
        UserInfoUserDetails principal = (UserInfoUserDetails) authentication.getPrincipal();

        if (!principal.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_ADMIN")) && principal.getUserId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't access other user accounts.");
        }

        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.userId")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDTO) {
        User updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<String> deleteUser(@PathVariable int userId, Authentication authentication) {
        String loggedInUserEmail = authentication.getName();
        User loggedInUser = userService.findByEmail(loggedInUserEmail);

        // Allow ADMIN to delete any user
        if (loggedInUser.getRole().equals("ADMIN")) {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully by admin");
        }

        if (loggedInUser.getRole().equals("USER")) {
            if (loggedInUser.getUserId() != userId) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can delete only your own account");
            }
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete users");
    }
    
}
