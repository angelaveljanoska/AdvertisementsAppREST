package com.example.advertisementsapp;

import com.example.advertisementsapp.models.Role;
import com.example.advertisementsapp.models.User;
import com.example.advertisementsapp.repositories.UserRepository;
import com.example.advertisementsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;

@SpringBootApplication
public class AdvertisementsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertisementsAppApplication.class, args);
    }
}
