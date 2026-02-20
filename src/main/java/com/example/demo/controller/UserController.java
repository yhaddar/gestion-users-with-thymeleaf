package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "add-user";
    }

    @PostMapping("/form-add")
    public String addUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                          BindingResult bindingResult) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "add-user";
        }

        userService.saveUser(userDTO);
        return "redirect:/users/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("userDTO", userService.getUserById(id));
        return "edit-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("userDTO") UserDTO userDTO,
                             BindingResult bindingResult,
                             Model model) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "edit-user";
        }

        userService.updateUser(id, userDTO);

        return "redirect:/users/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "redirect:/users/all";
    }
}