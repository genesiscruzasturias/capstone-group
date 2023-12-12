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

@Controller
public class ReviewController {

    private final ReviewRepository reviewDAO;
    private final UserRepository userDAO;

    public ReviewController(ReviewRepository reviewDAO, UserRepository userDAO) {
        this.reviewDAO = reviewDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/reviews")
    public String homePage(Model model) {
        ArrayList<Review> allPosts = new ArrayList<>(reviewDAO.findAll());
        model.addAttribute("allPosts", allPosts);
        return "index";
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
        review = reviewDAO.getPostById(1L);
        newReview.setBrewery(breweryId);
        newReview.setUser(user);
        newReview.setDescription(review.getDescription());
        newReview.setImage("image.jpg");
        newReview.setRating(10);
        reviewDAO.save(newReview);
//        model.addAttribute("brewery", brewery);
        model.addAttribute("review", newReview);
//        model.
//        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        return "redirect:/view-brewery?brewery=" + breweryId;
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(@PathVariable long id, Model model) {
        Review singlePost = reviewDAO.getPostById(id);
        model.addAttribute("singlePost", singlePost);
        return "view-brewery";
    }

}
