package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

// import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //Despite this beingset up looking the right way, it still isn't connecting well on the nav bar.
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

    @GetMapping("profile/edit-review")
    public String showEdit(@RequestParam Long reviewId, Model model){
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);
        model.addAttribute("review", existingReview);
        return "users/profile";
    }

    @PostMapping("profile/edit-review")
    public String editReview(@ModelAttribute Review editedReview){
        reviewRepository.save(editedReview);
        return "redirect:/view-brewery?brewery=" + editedReview.getBrewery();
    }

}

