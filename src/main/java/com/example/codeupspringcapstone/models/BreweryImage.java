package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "brewery_images")
public class BreweryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "img_path", nullable = false)
    private String imgPath;

    @OneToOne
    @JoinColumn (name = "review_id")
    private Review review;

    public BreweryImage(Long id, String imgPath, Review review) {
        this.id = id;
        this.imgPath = imgPath;
        this.review = review;
    }

    public BreweryImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return imgPath;
    }

    public void setImage(String image) {
        this.imgPath = imgPath;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
