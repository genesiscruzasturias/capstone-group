package com.example.codeupspringcapstone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "breweries")

public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private int postalCode;

    @Column(name = "url", nullable = false)
    private String url;

    public Brewery(Long id, String name, String address, String phoneNumber, String city, String state, int postalCode, String url) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.url = url;
    }

    public Brewery(String name, String address, String phoneNumber, String city, String state, int postalCode, String url) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.url = url;
    }

    public Brewery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
