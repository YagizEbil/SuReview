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

        // Professor Check
        String professorName = "Erhan Kavak";
        Professor professor = professorService.findByName(professorName);
        if (professor == null) {
            professor = new Professor();
            professor.setName(professorName);
            professor.setDepartment("Mechatronics Engineering");
            professor = professorService.save(professor);
        }

        // Course Check
        String courseTitle = "ME 304";
        Course course = courseService.findByTitle(courseTitle);
        if (course == null) {
            course = new Course();
            course.setTitle(courseTitle);
            course.setDescription("Motion Kinematics");
            course = courseService.save(course);
        }

        // Course Offering Check
        String semester = "Fall 2023";
        if (!courseOfferingService.offeringExists(course.getId(), professor.getId(), semester)) {
            CourseOffering offering = new CourseOffering();
            offering.setCourseId(course.getId());
            offering.setProfessorId(professor.getId());
            offering.setSemester(semester);
            courseOfferingService.save(offering);
        }
    }
}
