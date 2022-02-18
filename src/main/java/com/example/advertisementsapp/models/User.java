package com.example.advertisementsapp.models;

import com.example.advertisementsapp.utility.AuthDTO;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column
    String username;

    @NotNull
    @Column
    String password;

    @NotNull
    @Column
    Role role;

    @OneToMany(cascade=CascadeType.REMOVE)
    List<Advertisement> createdAdvertisements;

    public User() {}

    public User(String username, String password, Role role, List<Advertisement> createdAdvertisements) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAdvertisements = createdAdvertisements;
    }

    public static User from(AuthDTO authDTO) {
        User user = new User();
        user.username = authDTO.username;
        user.password = authDTO.password;
        user.role = Role.USER;
        user.createdAdvertisements = new ArrayList<>();
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Advertisement> getCreatedAdvertisements() {
        return createdAdvertisements;
    }

    public void setCreatedAdvertisements(ArrayList<Advertisement> createdAdvertisements) {
        this.createdAdvertisements = createdAdvertisements;
    }
}
