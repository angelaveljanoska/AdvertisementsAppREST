package com.example.advertisementsapp.controllers;

import com.example.advertisementsapp.models.Advertisement;
import com.example.advertisementsapp.models.Status;
import com.example.advertisementsapp.services.AdvertisementService;
import com.example.advertisementsapp.utility.AdvertisementDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("advertisement")
@CrossOrigin("*")
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping("/{id}")
    ResponseEntity<Advertisement> getAdvertisementById(@PathVariable Long id) {
    return ResponseEntity.ok(advertisementService.getAdvertisementById(id));
    }

    @GetMapping
    ResponseEntity<List<Advertisement>> getAllAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAllAdvertisements());
    }

    @PostMapping
    ResponseEntity<Advertisement> addAdvertisement(@RequestBody AdvertisementDTO advertisementDTO) {
        return ResponseEntity.ok(advertisementService.createAdvertisement(advertisementDTO));
    }

    @PutMapping("/{advertisementId}")
    ResponseEntity<Advertisement> updateAdvertisementStatus(@PathVariable Long advertisementId, @RequestBody Status status) throws Exception {
        return ResponseEntity.ok(advertisementService.updateAdvertisementStatus(advertisementId, status));
    }

    @PutMapping("/{advertisementId}/price")
    ResponseEntity<Advertisement> updateAdvertisementPrice(@PathVariable Long advertisementId, String price) {
            return ResponseEntity.ok(advertisementService.updateAdvertisementPrice(advertisementId, price));
    }

    @PostMapping("/{advertisementId}/rating")
    ResponseEntity<Advertisement> rateAdvertisement(@PathVariable Long advertisementId, @RequestBody Integer score) {
        return ResponseEntity.ok(advertisementService.rateAdvertisement(advertisementId, score));
    }
}
