//package com.example.codeupspringcapstone.controllers;
//
//import com.example.codeupspringcapstone.models.Review;
//import com.example.codeupspringcapstone.repositories.ReviewRepository;
//import com.example.codeupspringcapstone.services.EditService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class EditController {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @GetMapping
//    public String showEdit(@RequestParam Long reviewId, Model model){
//    Review existingReview = reviewRepository.findById(reviewId).orElse(null);
//    model.addAttribute("review", existingReview);
//    return "users/profile";
//    }
//
//    @PostMapping
//    public String editReview(@ModelAttribute Review editedReview){
//    reviewRepository.save(editedReview);
//    return "redirect:/view-brewery?brewery=" + editedReview.getBrewery();
//    }
//
//}
