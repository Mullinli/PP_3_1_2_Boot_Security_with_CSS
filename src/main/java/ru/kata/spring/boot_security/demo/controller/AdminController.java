package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
public class AdminController {

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImp;

    @GetMapping("/admin")
    public String showUserListForAdmin(@ModelAttribute("user") User user, Authentication authentication, Model model) {
        model.addAttribute("thisuser", authentication.getPrincipal());
        model.addAttribute("newuser", user);
        model.addAttribute("users", userServiceImp.findAll());
        model.addAttribute("roles", roleServiceImpl.showRoles());
        return "admin";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        userServiceImp.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImp.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
       userServiceImp.update(id, user);
        return "redirect:/admin";
    }

}
