package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.CourseOffering;
import com.sabanciuniv.sureview.service.CourseOfferingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseOfferings")
@RequiredArgsConstructor
public class CourseOfferingController {
    private static final Logger logger = LoggerFactory.getLogger(CourseOfferingController.class);
    private final CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<CourseOffering> createCourseOffering(@RequestBody CourseOffering courseOffering) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        CourseOffering createdCourseOffering = courseOfferingService.save(courseOffering);
        return ResponseEntity.ok(createdCourseOffering);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseOffering> getCourseOfferingById(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        CourseOffering courseOffering = courseOfferingService.findById(id);
        return ResponseEntity.ok(courseOffering);
    }

    @GetMapping
    public ResponseEntity<Iterable<CourseOffering>> getAllCourseOfferings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        Iterable<CourseOffering> courseOfferings = courseOfferingService.findAll();
        return ResponseEntity.ok(courseOfferings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseOffering(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated user: {}", auth.getName());
        courseOfferingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}