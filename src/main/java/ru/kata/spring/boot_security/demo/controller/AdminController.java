package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Controller
public class AdminController {

    @Autowired
    RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String showUserListForAdmin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String showSignUpForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles",roleService.showRoles());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.delete(userRepository.findById(id).orElse(null));
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("roles", roleService.showRoles());
        model.addAttribute("user" , user);
        return "update-user";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!user.getPassword().equals(userRepository.findById(id).get().getPassword())) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        return "redirect:/admin";
    }

}
