package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

// import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private ReviewRepository reviewRepository;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(ReviewRepository reviewRepository, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.reviewRepository = reviewRepository;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getAllUsers() {
        return "index";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        // Check if username already exists
        if (userDao.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Username unavailable, please enter a new username");
            // Clear the username field
            user.setUsername("");
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        // Check if email already exists
        if (userDao.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email already in use, please try a different one");
            // Clear the email field
            user.setEmail("");
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("user", user);
            return "users/sign-up";
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userDao.save(user);
        return "redirect:/sign-in";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUsersById(loggedInUser.getId());
        model.addAttribute("user", user);
        Long userId = loggedInUser.getId();
        List<Review> userReview = reviewRepository.findByUserId(userId);

        model.addAttribute("userReviews", userReview);
        return "users/profile";
    }

//    @PostMapping("/profile")
//    public String saveProfilePic(@ModelAttribute User user, Model model, @RequestParam String profileImgURL) {
//        model.addAttribute("user", user);
//        System.out.println(user.getUsername());
//        System.out.println(user.getProfilePicURL());
//        System.out.println("THIS IS THE URLLLLLLLLLLL: " + profileImgURL);
//        user.setProfilePicURL(profileImgURL);
//        userDao.save(user);
//        return "redirect:/profile";
//    }

    @GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        User user = userDao.getUsersById(id);
        model.addAttribute("user", user);
        return "users/edit-profile"; // Corrected the return path
    }

    //this one works
    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute User user) {
        System.out.println(user.getPassword());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userDao.save(user);
        return "redirect:/profile";
    }


    @PostMapping("/delete-profile")
    public String deleteProfile() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.deleteById(loggedInUser.getId());
        return "redirect:/sign-in";
    }
}



