package com.example.codeupspringcapstone.controllers;


import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    //Despite this beingset up looking the right way, it still isn't connecting well on the nav bar.
    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:users/sign-up";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", loggedInUser);
        return "users/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", new User());
        return "users/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String saveEditedProfile(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:users/profile";
    }

}
