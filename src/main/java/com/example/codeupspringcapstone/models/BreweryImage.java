package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "brewery_images")
public class BreweryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn (name = "id")
    private Review review;

    public BreweryImage(Long id, String image, String name, Review review) {
        this.id = id;
        this.image = image;
        this.name = name;
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
