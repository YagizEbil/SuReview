package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.CourseOffering;
import com.sabanciuniv.sureview.service.CourseOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseOfferings")
@RequiredArgsConstructor
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<CourseOffering> createCourseOffering(@RequestBody CourseOffering courseOffering) {
        CourseOffering createdCourseOffering = courseOfferingService.save(courseOffering);
        return ResponseEntity.ok(createdCourseOffering);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseOffering> getCourseOfferingById(@PathVariable String id) {
        CourseOffering courseOffering = courseOfferingService.findById(id);
        return ResponseEntity.ok(courseOffering);
    }

    @GetMapping
    public ResponseEntity<Iterable<CourseOffering>> getAllCourseOfferings() {
        Iterable<CourseOffering> courseOfferings = courseOfferingService.findAll();
        return ResponseEntity.ok(courseOfferings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseOffering(@PathVariable String id) {
        courseOfferingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
