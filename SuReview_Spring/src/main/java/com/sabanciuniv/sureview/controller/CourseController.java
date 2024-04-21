package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.Course;
import com.sabanciuniv.sureview.repository.CourseRepository;
import com.sabanciuniv.sureview.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.save(course);
        return ResponseEntity.ok(createdCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Course course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        Iterable<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course courseDetails) {
        Course course = courseService.findById(id);
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        final Course updatedCourse = courseService.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
