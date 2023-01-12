package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showUserListForAdmin(@ModelAttribute("user") User user, Authentication authentication, Model model) {
        model.addAttribute("thisuser", authentication.getPrincipal());
        model.addAttribute("newuser", user);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.showRoles());
        return "admin";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        userService.update(id, user);
        return "redirect:/admin";
    }

}
