package com.example.advertisementsapp.services;

import com.example.advertisementsapp.models.Advertisement;
import com.example.advertisementsapp.models.Rating;
import com.example.advertisementsapp.models.Status;
import com.example.advertisementsapp.models.User;
import com.example.advertisementsapp.repositories.AdvertisementRepository;
import com.example.advertisementsapp.repositories.RatingRepository;
import com.example.advertisementsapp.repositories.UserRepository;
import com.example.advertisementsapp.utility.AdvertisementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public Advertisement getAdvertisementById(Long id) {
        return advertisementRepository.findById(id).get();
    }

    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    public Advertisement createAdvertisement(AdvertisementDTO advertisementDTO) {
        User user = userService.findByUsername(advertisementDTO.creatorUsername);
        Advertisement ad = Advertisement.from(advertisementDTO);
        ad.setCreator(user);
        Advertisement savedAdvertisement = advertisementRepository.save(ad);
        user.getCreatedAdvertisements().add(savedAdvertisement);
        userRepository.save(user);
        return savedAdvertisement;
    }

    public Advertisement updateAdvertisementStatus(Long advertisementId, Status status) {
        Advertisement ad = advertisementRepository.findById(advertisementId).orElseThrow();
        ad.setStatus(status);
        return advertisementRepository.save(ad);
    }

    public Advertisement updateAdvertisementPrice(Long advertisementId, String price) {
        Advertisement ad = advertisementRepository.findById(advertisementId).orElseThrow();
        ad.setPrice(price);
        return advertisementRepository.save(ad);
    }

    public Advertisement rateAdvertisement(Long advertisementId, Integer score) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId).orElseThrow();
        Rating rating = new Rating(advertisement, score);
        advertisement.getRatings().add(rating);
        ratingRepository.save(rating);
        return advertisementRepository.save(advertisement);
    }
}
