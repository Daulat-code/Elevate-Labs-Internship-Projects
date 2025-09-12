package com.cb.daulat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

import com.cb.daulat.service.serviceImpl.UserServiceImpl;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    
    @GetMapping("/Deshboard")
    public String home() {
        return "Deshboard"; 
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        userService.registerUser(email, password);
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("/home")
    public String home(Authentication authentication, Model model,
                       @AuthenticationPrincipal Object principal) {

        String name = authentication.getName(); // default username/email
        String picture = null;

        // If user logged in via Google/LinkedIn
        if (principal instanceof OAuth2User oauthUser) {
            name = (String) oauthUser.getAttributes().getOrDefault("name", name);
            picture = (String) oauthUser.getAttributes().get("picture"); // Google provides this
            if (picture == null && oauthUser.getAttributes().get("profilePicture") != null) {
                // LinkedIn API may provide profilePicture field depending on scope
                picture = oauthUser.getAttributes().get("profilePicture").toString();
            }
        }

        model.addAttribute("name", name);
        model.addAttribute("picture", picture);

        return "home";
    }
}
