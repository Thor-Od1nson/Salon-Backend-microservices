package com.vishal.review_service.service;

import com.vishal.review_service.model.Review;

import com.vishal.review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repo;

    public Review addReview(Review review) {
        return repo.save(review);
    }

    public List<Review> getBySalon(Long salonId) {
        return repo.findBySalonId(salonId);
    }

    public double getAverage(Long salonId) {
        List<Review> reviews = repo.findBySalonId(salonId);

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}