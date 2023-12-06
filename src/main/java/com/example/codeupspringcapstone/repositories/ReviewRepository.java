package com.example.codeupspringcapstone.repositories;

import com.example.codeupspringcapstone.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review getPostById(long id);

}
