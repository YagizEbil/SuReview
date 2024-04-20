package com.sabanciuniv.sureview.service;

import com.sabanciuniv.sureview.model.Course;
import com.sabanciuniv.sureview.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService implements GenericService<Course> {
    private final CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course findByTitle(String title) {
        return courseRepository.findByTitle(title).orElse(null);
    }

        @Override
    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        courseRepository.deleteById(id);
    }
}
