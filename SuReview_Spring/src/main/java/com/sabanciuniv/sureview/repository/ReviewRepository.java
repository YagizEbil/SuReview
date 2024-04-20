package com.sabanciuniv.sureview.repository;

import com.sabanciuniv.sureview.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByCourseOfferingId(String courseOfferingId);
}
