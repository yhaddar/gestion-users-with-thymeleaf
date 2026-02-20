package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO saveUser(@Valid UserDTO userDTO);
    UserDTO findByEmail(String email);
    UserDTO updateUser(Long id, @Valid UserDTO userDTO);
    UserDTO partialUser(Long id, UserDTO userDTO);
    boolean deleteUser(Long id);
}
