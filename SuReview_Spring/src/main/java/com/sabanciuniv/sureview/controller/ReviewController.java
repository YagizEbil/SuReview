package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.Review;
import com.sabanciuniv.sureview.model.CourseOffering;
import com.sabanciuniv.sureview.repository.ReviewRepository;
import com.sabanciuniv.sureview.repository.CourseOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {


    private ReviewRepository reviewRepository;
    private CourseOfferingRepository courseOfferingRepository;

    // POST endpoint to create a new review for a specific course offering
    @PostMapping("/{courseOfferingId}")
    public ResponseEntity<Review> createReview(@PathVariable String courseOfferingId, @RequestBody Review review, Authentication authentication) {
        CourseOffering courseOffering = courseOfferingRepository.findById(courseOfferingId)
                .orElseThrow(() -> new RuntimeException("Course offering not found"));

        review.setCourseOffering(courseOffering);
        review.setUserId(authentication.getName()); // Set the user ID (email) in the review
        Review savedReview = reviewRepository.save(review);
        return ResponseEntity.ok(savedReview);
    }

    // GET endpoint to fetch reviews for a specific course offering
    @GetMapping("/course/{courseOfferingId}")
    public ResponseEntity<List<Review>> getReviewsByCourseOffering(@PathVariable String courseOfferingId) {
        List<Review> reviews = reviewRepository.findByCourseOfferingId(courseOfferingId);
        return ResponseEntity.ok(reviews);
    }
}
