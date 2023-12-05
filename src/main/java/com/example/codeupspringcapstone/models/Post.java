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

    @Column(name = "image")
    private String image;

    @Column(name = "desc")
    private String desc;
    @Column(name = "brewery")
    private String brewery;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<Comment> comments;

    public Post() {}

    public Post(Post copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        image = copy.image;
        desc = copy.desc;
        brewery = copy.brewery;
        user = copy.user;
        comments = copy.comments;
    }

    public Post(String image, String desc, String brewery, List<Comment> comments) {
        this.image = image;
        this.desc = desc;
        this.brewery = brewery;
        this.comments = comments;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage () {
        return image;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setBrewery (String brewery) {
        this.brewery = brewery;
    }

    public String getBrewery () {
        return brewery;
    }

    public void setComments (List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments () {
        return comments;
    }

    public void setUser (User user) { this.user = user; }

    public User getUser() { return user; }

}
