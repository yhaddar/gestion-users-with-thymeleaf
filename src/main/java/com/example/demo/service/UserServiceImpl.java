package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> user = this.userRepository.findAll();
        List<UserDTO> userMapper = new ArrayList<UserDTO>();

        for(User u: user){
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(u.getEmail());
            userDTO.setRole(u.getRole());
            userDTO.setId(u.getId());
            userDTO.setName(u.getName());
            userMapper.add(userDTO);
        }

        return userMapper;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        return userDTO;

    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        String password = this.bCryptPasswordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPassword(password);
        this.userRepository.save(user);

        return userDTO;
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO partialUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }
}
