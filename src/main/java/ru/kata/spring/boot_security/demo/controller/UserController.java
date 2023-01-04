package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.repo.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String getWelcome() {
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String showUserList(Authentication authentication, Model model) {
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }

}


