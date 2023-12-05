package com.example.codeupspringcapstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.codeupspringcapstone.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User getUsersById(long id);
}
