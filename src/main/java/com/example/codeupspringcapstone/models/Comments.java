package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {

//    CREATING COLUMNS AND RELATIONSHIPS IN THE DATABASE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

//CREATE CONSTRUCTORS

    public Comments(){}

    public Comments(Long id, String comment, Post post) {
        this.id = id;
        this.comment = comment;
        this.post = post;
    }
    public Comments(Long id, String comment, User user, Post post) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.post = post;
    }

//    GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
