package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "video")
    private String video;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Post() {}
    public Post(String title, String video) {
        this.title = title;
        this.video = video;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getTitle () {
        return title;
    }

    public void setVideo (String video) {
        this.video = video;
    }

    public String getVideo () {
        return video;
    }

    public void setUser (User user) { this.user = user; }

    public User getUser() { return user; }

}
