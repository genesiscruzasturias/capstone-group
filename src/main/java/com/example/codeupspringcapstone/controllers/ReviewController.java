package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
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

    @GetMapping("/reviews/create")
    public String setPostModel(Model model) {
        model.addAttribute("post", new Review());
        return "create-review";
    }

    @PostMapping("/reviews/create")
    public String createPost(@ModelAttribute Review post){
        User user = userDAO.getUsersById(1L);
        post.setUser(user);
        reviewDAO.save(post);
//        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        return "redirect:/view-all-posts";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(@PathVariable long id, Model model) {
        Review singlePost = reviewDAO.getPostById(id);
        model.addAttribute("singlePost", singlePost);
        return "view-brewery";
    }

}
