package com.example.codeupspringcapstone.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="users")
public class User {

    //    CREATING COLUMN FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;

    // CREATING RELATIONSHIPS
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Review> reviews;

    // CREATING CONSTRUCTORS
    public User(){}

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        reviews = copy.reviews;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String imgPath) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // GETTERS AND SETTERS
    public void setId (Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername () {
        return username;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getEmail () {
        return email;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getPassword () {
        return password;
    }


}
