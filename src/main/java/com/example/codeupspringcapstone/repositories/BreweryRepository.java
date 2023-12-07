package com.example.codeupspringcapstone.repositories;

import com.example.codeupspringcapstone.models.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {
    Brewery getPostById(long id);


}
