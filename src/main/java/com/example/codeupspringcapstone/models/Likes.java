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

    @Column(name = "like", nullable = false)
    private String like;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "review_id")
    private Review review;

//CREATE CONSTRUCTORS


    public Likes(Long id, String like, User user, Review review) {
        this.id = id;
        this.like = like;
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

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
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
