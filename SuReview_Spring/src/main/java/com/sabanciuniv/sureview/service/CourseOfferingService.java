package com.sabanciuniv.sureview.service;

import com.sabanciuniv.sureview.model.CourseOffering;
import com.sabanciuniv.sureview.repository.CourseOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseOfferingService implements GenericService<CourseOffering> {
    private final CourseOfferingRepository courseOfferingRepository;

    @Override
    public CourseOffering save(CourseOffering courseOffering) {
        return  courseOfferingRepository.save(courseOffering);
    }

    @Override
    public CourseOffering findById(String id) {
        return courseOfferingRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Offering not found"));
    }

    @Override
    public Iterable<CourseOffering> findAll() {
        return courseOfferingRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        courseOfferingRepository.deleteById(id);
    }

    public boolean offeringExists(String courseId, String professorId, String semester) {
        return courseOfferingRepository.findByCourseIdAndProfessorIdAndSemester(courseId, professorId, semester).isPresent();
    }

    public CourseOffering findByCourseIdProfessorIdAndSemester(String courseId, String professorId, String semester) {
        return courseOfferingRepository.findByCourseIdAndProfessorIdAndSemester(courseId, professorId, semester)
                .orElseThrow(() -> new RuntimeException("No course offering found for the specified criteria"));
    }
}
