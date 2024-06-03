package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.Course;
import com.sabanciuniv.sureview.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        Course createdCourse = courseService.save(course);
        return ResponseEntity.ok(createdCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        Course course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        Iterable<Course> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course courseDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        Course course = courseService.findById(id);
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        final Course updatedCourse = courseService.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
