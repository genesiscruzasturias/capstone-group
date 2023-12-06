package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {

//    CREATING COLUMNS AND RELATIONSHIPS IN THE DATABASE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "likes", nullable = false)
    private String likes;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "review_id")
    private Review review;

//CREATE CONSTRUCTORS


    public Likes(Long id, String likes, User user, Review review) {
        this.id = id;
        this.likes = likes;
        this.user = user;
        this.review = review;
    }

    public Likes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String like) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
