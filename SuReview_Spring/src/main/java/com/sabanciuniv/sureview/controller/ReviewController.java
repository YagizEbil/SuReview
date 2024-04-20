package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.Review;
import com.sabanciuniv.sureview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/courseOffering/{courseOfferingId}")
    public ResponseEntity<List<Review>> getReviewsByCourseOffering(@PathVariable String courseOfferingId) {
        List<Review> reviews = reviewService.getReviewsByCourseOfferingId(courseOfferingId);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}
