package com.sabanciuniv.sureview.repository;

import com.sabanciuniv.sureview.model.CourseOffering;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseOfferingRepository extends MongoRepository<CourseOffering, String> {
    List<CourseOffering> findByCourseId(String courseId);
    List<CourseOffering> findByProfessorId(String professorId);
}
