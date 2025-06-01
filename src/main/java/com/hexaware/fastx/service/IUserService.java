package com.hexaware.fastx.service;

import java.util.List;

import com.hexaware.fastx.dto.AuthResponseDTO;
import com.hexaware.fastx.dto.LoginRequestDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entity.User;

import jakarta.validation.Valid;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User createUser(User user);
    User updateUser(int id, User user);
    User findByEmail(String email);
	String registerUser(@Valid UserDTO userDTO);
	AuthResponseDTO loginUser(LoginRequestDTO loginDTO);
    void deleteUser(int id);
}
