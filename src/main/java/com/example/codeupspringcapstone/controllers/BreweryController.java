package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Brewery;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import com.example.codeupspringcapstone.repositories.BreweryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BreweryController {

    private final BreweryRepository breweryDAO;
    private final UserRepository userDAO;
    private final ReviewRepository reviewDAO;

    public BreweryController(UserRepository userDAO, BreweryRepository breweryDAO, ReviewRepository reviewDAO) {
        this.breweryDAO = breweryDAO;
        this.userDAO = userDAO;
        this.reviewDAO = reviewDAO;
    }

    @GetMapping("/view-breweries")
    public String homePage (Model model) {
        model.addAttribute("breweryModel", new Brewery());
        return "view-breweries";
    }

    @PostMapping("/view-breweries")
    public String createBreweryModel(@ModelAttribute Brewery breweryModel, Model model) {
        model.addAttribute("model", breweryModel);
        breweryDAO.save(breweryModel);
        return "redirect:/view-brewery";
    }

    @GetMapping("/view-brewery")
    public String viewBrewery () {
        return "view-brewery";
    }

}
