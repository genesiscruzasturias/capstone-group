package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class BreweryController {

//    private final BreweryRepository breweryDAO;
    private final UserRepository userDAO;
    private final ReviewRepository reviewDAO;

    public BreweryController(UserRepository userDAO, ReviewRepository reviewDAO) {
//        this.breweryDAO = breweryDAO;
        this.userDAO = userDAO;
        this.reviewDAO = reviewDAO;
    }


    @GetMapping("/view-breweries")
    public String homePage (Model model) {
//        model.addAttribute("breweryModel", new Brewery());
        return "view-breweries";
    }

    @PostMapping("/view-breweries")
    public String createBreweryModel(Model model) {
//        model.addAttribute("model", breweryModel);
//        breweryDAO.save(breweryModel);
        return "redirect:/view-brewery";
    }

    @GetMapping("/view-brewery")
    public String createReview(Model model){
        model.addAttribute("review", new Review());
        return "view-brewery";
    }

    @PostMapping("/view-brewery")
    public String viewBrewery (@RequestParam String breweryId) {
        User user = userDAO.getUsersById(1L);
//        Brewery brewery = breweryDAO.getBreweryByBrewery_id(breweryId);
        Review review = new Review();
//        review.set(breweryId);
        review.setBrewery(breweryId);
        review.setUser(user);
        reviewDAO.save(review);
//        model.addAttribute("brewery", brewery);
//        model.addAttribute("review", review);
//        model.
        return "redirect:/view-brewery";
    }

}
