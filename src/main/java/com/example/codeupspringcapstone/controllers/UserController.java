package com.example.codeupspringcapstone.controllers;


import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.List;

@Controller
public class UserController {
    private ReviewRepository reviewRepository;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
//    private Review userReview;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public UserController(ReviewRepository reviewRepository, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.reviewRepository = reviewRepository;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

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
        return "redirect:users/sign-in";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", loggedInUser);
        Long userId = loggedInUser.getId();
        List <Review> userReview = reviewRepository.findByUserId(userId);

        model.addAttribute("userReviews", userReview);
        return "users/profile";
    }
//    @GetMapping("/profile")
//    public String showProfile(Model model) {
//        // Fetch the review for the current user (example)
//
//        return "profile";
//    }

}
