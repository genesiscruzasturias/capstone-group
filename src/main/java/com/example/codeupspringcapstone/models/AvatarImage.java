package com.example.codeupspringcapstone.models;


import jakarta.persistence.*;

@Entity
@Table(name = "avatar_images")
public class AvatarImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    public AvatarImage(Long id, String image, String name, User user) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.user = user;
    }

    public AvatarImage() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
