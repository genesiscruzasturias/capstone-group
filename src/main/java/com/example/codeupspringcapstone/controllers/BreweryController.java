package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import com.example.codeupspringcapstone.repositories.BreweryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String homePage () {
        return "view-breweries";
    }

}
