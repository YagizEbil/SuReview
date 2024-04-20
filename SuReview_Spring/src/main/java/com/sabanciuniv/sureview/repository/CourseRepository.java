package com.sabanciuniv.sureview.repository;

import com.sabanciuniv.sureview.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {
    // You can add methods to retrieve courses by specific criteria
    Optional<Course> findByTitle(String title);
}
