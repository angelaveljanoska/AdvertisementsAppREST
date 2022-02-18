package com.example.advertisementsapp.repositories;

import com.example.advertisementsapp.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
