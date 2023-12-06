package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

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
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_image")
    private String imgPath;

    // CREATING RELATIONSHIPS
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> posts;

    // CREATING CONSTRUCTORS
    public User(){}

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        posts = copy.posts;
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
        this.imgPath = imgPath;
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

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public String getImgPath() {
        return imgPath;
    }

}
