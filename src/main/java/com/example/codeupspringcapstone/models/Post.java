package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comments> comments;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    //    CREATING CONSTRUCTORS
    public Post(){}
    public Post(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public Post(User user, String image, String description) {
        this.user = user;
        this.image = image;
        this.description = description;
    }

    public Post(long id, String image, String description) {
        this.id = id;
        this.image = image;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUser (User user) { this.user = user; }

    public User getUser() { return user; }
}
