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
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/")
    public String getAllUsers(Model model) {
        return "index";
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


//    @GetMapping("/profile/edit/{id}")
//    public String editProfile(Model model) {
//        model.addAttribute("user", new User());
//        return "users/edit-profile/}{id}";
//    }
//
//    @PostMapping("/profile/edit")
//public String updateProfile(@ModelAttribute @Valid User updatedUser, BindingResult result, Model model, @RequestParam Long id) {
//        long loggedInUserId = id;
//
//if (result.hasErrors()) {
//model.addAttribute("errors", result.getAllErrors());
//model.addAttribute("user", updatedUser);
//System.out.println("User password is: " + updatedUser.getPassword());
//return "redirect:/profile/edit"; // Return to the edit-profile page if errors occur
//}
//updatedUser =new User(loggedInUserId, updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getPassword());
//userDao.save(updatedUser);
//return "redirect:/profile";
//}
@GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        // Find the user by ID
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "users/edit-profile"; // Corrected the return path
        } else {
            return "redirect:/"; // Redirect to home or an error page if user not found
        }
    }

    @PostMapping("/profile/edit/{id}")
    public String updateProfile(@PathVariable Long id, @ModelAttribute @Valid User updatedUser, BindingResult result, Model model) {
        // Find the existing user
        Optional<User> existingUserOptional = userDao.findById(id);
        if (!existingUserOptional.isPresent()) {
            return "redirect:/"; // Redirect if user not found
        }
        User existingUser = existingUserOptional.get();

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("user", updatedUser);
            return "users/edit-profile";
        }

        // Update the user's details
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        if (!updatedUser.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
            existingUser.setPassword(hashedPassword);
        }
        userDao.save(existingUser);
        return "redirect:/profile";
    }
    @PostMapping("/profile/delete")
    public String deleteProfile() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.deleteById(loggedInUser.getId());
        return "redirect:/login";
    }
//}
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
//    @Controller
//    @RequestMapping("/profile")
//    public class ProfileController {
//        @Autowired
//        private UserRepository userRepository;

        // Edit profile
//        @GetMapping("/edit/{id}")
//        public String editProfile(@PathVariable("id") Long id, Model model) {
//            Optional<User> userOptional = userRepository.findById(id);
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                model.addAttribute("user", user);
//                return "edit_profile";
//            } else {
//                return "error";
//            }
//        }

//        @PostMapping("/edit")
//        public String saveProfile(@ModelAttribute("user") User user) {
//            userRepository.save(user);
//            return "redirect:/profile";
//        }
    // method to delete user profile
//          @PostMapping("/delete-profile")
//public String deleteProfile() {
//User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//userDao.deleteById(loggedInUser.getId());
//return "redirect:/login";
//}
//}


