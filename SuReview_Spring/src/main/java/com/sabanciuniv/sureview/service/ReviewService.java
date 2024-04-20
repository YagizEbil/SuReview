package com.sabanciuniv.sureview.service;


import com.sabanciuniv.sureview.model.Review;
import com.sabanciuniv.sureview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
    public List<Review> getReviewsByCourseOfferingId(String courseOfferingId) {
        return reviewRepository.findByCourseOfferingId(courseOfferingId);
    }
    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }
}
