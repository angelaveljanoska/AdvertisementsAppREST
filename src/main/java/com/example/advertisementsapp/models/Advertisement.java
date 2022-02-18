package com.example.advertisementsapp.models;

import com.example.advertisementsapp.utility.AdvertisementDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String description;

    @Column
    String price;

    @Column
    String imageUrl;

    @Column
    Date datePosted;

    @Column
    Status status;

    @OneToMany
    List<Rating> ratings;

    @JsonIgnore
    @OneToOne
    User creator;

    public Advertisement() {}

    public Advertisement(String name, String description, String price, String imageUrl, Date datePosted, Status status, List<Rating> ratings, User creator) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.datePosted = datePosted;
        this.status = status;
        this.ratings = ratings;
        this.creator = creator;
    }

    public static Advertisement from(AdvertisementDTO advertisementDTO) {
        Advertisement ad = new Advertisement();
        ad.name = advertisementDTO.name;
        ad.description = advertisementDTO.description;
        ad.price = advertisementDTO.price;
        ad.imageUrl = advertisementDTO.imageUrl;
        ad.datePosted = Date.from(Instant.now());
        ad.status = Status.PENDING;
        return ad;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getCreatorUsername() {
        return creator.username;
    }
}
