package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userDAO;

    public ReviewController(ReviewRepository reviewRepository, UserRepository userDAO) {
        this.reviewRepository = reviewRepository;
        this.userDAO = userDAO;
    }



    @GetMapping("/create")
    public String setPostModel(Model model) {
        String defaultId = "default_id";
        model.addAttribute("review", new Review());
        model.addAttribute("breweryId", defaultId);

        return "create-review";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Review review, Model model, @RequestParam String breweryId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review newReview = new Review();
//        String breweryId = "random-brewery-id";
        review = reviewRepository.getPostById(1L);
        newReview.setBrewery(breweryId);
        newReview.setUser(user);
        newReview.setDescription(review.getDescription());
        newReview.setImage("image.jpg");
        newReview.setRating(10);
        reviewRepository.save(newReview);
//        model.addAttribute("brewery", brewery);
        model.addAttribute("review", newReview);
//        model.
//        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        return "redirect:/view-brewery?brewery=" + breweryId;
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(@PathVariable long id, Model model) {
        Review singlePost = reviewRepository.getPostById(id);
        model.addAttribute("singlePost", singlePost);
        return "view-brewery";
    }

    @PostMapping("/profile/edit-review")
    public String editReview(@RequestParam Long reviewId, @RequestParam String editedDescription) {
        // Retrieve the existing review from the repository
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);

        // Update the description if the review exists
        if (existingReview != null) {
            existingReview.setDescription(editedDescription);
            reviewRepository.save(existingReview);
            return "redirect:/profile";
        }
        return "redirect:/error";
    }

    @DeleteMapping("/profile/delete-review/{id}")
    public String deleteReview(@PathVariable long id) {
        System.out.println("Does this run?");
        reviewRepository.deleteById(id);
        return "redirect:/index";
    }

}
