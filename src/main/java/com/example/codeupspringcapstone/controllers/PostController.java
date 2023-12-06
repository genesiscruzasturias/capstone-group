package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.PostRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDAO;
    private final UserRepository userDAO;

    public PostController(PostRepository postDAO, UserRepository userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/posts")
    public String homePage(Model model) {
        ArrayList<Review> allPosts = new ArrayList<>(postDAO.findAll());
        model.addAttribute("allPosts", allPosts);
        return "index";
    }

    @GetMapping("/posts/create")
    public String setPostModel(Model model) {
        model.addAttribute("post", new Review());
        return "create-posts";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Review post){
        User user = userDAO.getUsersById(1L);
        post.setUser(user);
        postDAO.save(post);
//        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(@PathVariable long id, Model model) {
        Review singlePost = postDAO.getPostById(id);
        model.addAttribute("singlePost", singlePost);
        return "post";
    }

}
