package com.example.codeupspringcapstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/sign-in")
    public String showLoginForm() {
        return "users/sign-in";
    }

}
