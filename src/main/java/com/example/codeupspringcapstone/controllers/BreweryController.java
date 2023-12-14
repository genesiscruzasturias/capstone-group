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
    public String createReview(Model model, @RequestParam(name = "brewery") String breweryId, User user){
        model.addAttribute("review", new Review());
        ArrayList<Review> breweryReviews = new ArrayList<>(reviewDAO.findReviewsByBrewery(breweryId));
        model.addAttribute("listOfReviews", breweryReviews);
        return "view-brewery";
    }

    @PostMapping("/view-brewery")
    public String viewBrewery (@ModelAttribute Review review, Model model, @RequestParam String breweryId) {
        review.setBrewery(breweryId);
        review.setDescription(review.getDescription());
        review.setImage(review.getImage());
        review.setRating(review.getRating());
        reviewDAO.save(review);
        model.addAttribute("breweryId", breweryId);
        return "redirect:/view-brewery?brewery=" + breweryId;
    }

}
