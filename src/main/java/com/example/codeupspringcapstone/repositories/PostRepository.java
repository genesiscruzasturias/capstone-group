package com.example.codeupspringcapstone.repositories;

import com.example.codeupspringcapstone.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post getPostById(long id);

}
