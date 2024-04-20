package com.sabanciuniv.sureview.config;

import com.sabanciuniv.sureview.model.Course;
import com.sabanciuniv.sureview.model.Professor;
import com.sabanciuniv.sureview.model.CourseOffering;
import com.sabanciuniv.sureview.service.CourseService;
import com.sabanciuniv.sureview.service.ProfessorService;
import com.sabanciuniv.sureview.service.CourseOfferingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final CourseOfferingService courseOfferingService;

    @Override
    public void run(String... args) throws Exception {
        Professor professor = new Professor();
        professor.setName("Kamer Kaya");
        professor.setDepartment("Computer Science");
        professor = professorService.save(professor);

        Course course = new Course();
        course.setTitle("CS204");
        course.setDescription("Advanced Programming");
        course = courseService.save(course);

        CourseOffering offering = new CourseOffering();
        offering.setCourseId(course.getId());
        offering.setProfessorId(professor.getId());
        offering.setSemester("Fall 2023");
        courseOfferingService.save(offering);
    }
}
