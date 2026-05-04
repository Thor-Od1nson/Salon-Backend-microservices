package com.vishal.review_service.controller;

import com.vishal.review_service.model.Review;
import com.vishal.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    public Review add(@RequestBody Review review) {
        return service.addReview(review);
    }

    @GetMapping("/salon/{salonId}")
    public List<Review> getBySalon(@PathVariable Long salonId) {
        return service.getBySalon(salonId);
    }

    @GetMapping("/salon/{salonId}/average")
    public double avg(@PathVariable Long salonId) {
        return service.getAverage(salonId);
    }
}