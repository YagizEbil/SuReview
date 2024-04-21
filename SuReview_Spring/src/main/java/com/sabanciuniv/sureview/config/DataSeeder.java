package com.sabanciuniv.sureview.config;

import com.sabanciuniv.sureview.model.*;
import com.sabanciuniv.sureview.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final CourseOfferingService courseOfferingService;
    private final UserService userService;
    private final ReviewService reviewService;

    @Override
    public void run(String... args) throws Exception {
        seedProfessors();
        seedCourses();
        seedCourseOfferings();
        seedUsers();
        seedReviews();
    }

    private void seedProfessors() {
        String professorName = "Erhan Kavak";
        Professor professor = professorService.findByName(professorName);
        if (professor == null) {
            professor = new Professor();
            professor.setName(professorName);
            professor.setDepartment("Mechatronics Engineering");
            professorService.save(professor);
        }
    }

    private void seedCourses() {
        String courseTitle = "ME 304";
        Course course = courseService.findByTitle(courseTitle);
        if (course == null) {
            course = new Course();
            course.setTitle(courseTitle);
            course.setDescription("Motion Kinematics");
            courseService.save(course);
        }
    }

    private void seedCourseOfferings() {
        String professorName = "Erhan Kavak";
        Professor professor = professorService.findByName(professorName);
        String courseTitle = "ME 304";
        Course course = courseService.findByTitle(courseTitle);
        String semester = "Fall 2023";
        if (professor != null && course != null && !courseOfferingService.offeringExists(course.getId(), professor.getId(), semester)) {
            CourseOffering offering = new CourseOffering();
            offering.setCourseId(course.getId());
            offering.setProfessorId(professor.getId());
            offering.setSemester(semester);
            courseOfferingService.save(offering);
        }
    }

    private void seedUsers() {
        String userEmail = "example@sabanciuniv.edu";
        User existingUser = userService.findByEmail(userEmail);
        if (existingUser == null) {
            User user = new User();
            user.setEmail(userEmail);
            user.setDisplayName("John Doe");
            userService.save(user);
        }
    }

    private void seedReviews() {
        String userEmail = "example@sabanciuniv.edu";
        User user = userService.findByEmail(userEmail);
        if (user != null) {
            String courseTitle = "ME 304";
            Course course = courseService.findByTitle(courseTitle);
            if (course != null) {
                Professor professor = professorService.findByName("Erhan Kavak");
                if (professor != null) {
                    CourseOffering courseOffering = courseOfferingService.findByCourseIdProfessorIdAndSemester(course.getId(), professor.getId(), "Fall 2023");
                    if (courseOffering != null) {
                        Review review = new Review();
                        review.setUserId(user.getId());
                        review.setCourseOffering(courseOffering);
                        review.setContent("Fuck this course.");
                        review.setRating(0);
                        review = reviewService.saveReview(review);
                    }
                }
            }
        }
    }

}
