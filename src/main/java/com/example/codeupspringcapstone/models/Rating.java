package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn (name = "id")
    private Review review;

    public Rating(Long id, Review review) {
        this.id = id;
        this.review = review;
    }

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
