package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.Professor;
import com.sabanciuniv.sureview.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;
    private final AuthController authController;

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        professor.setCreatedBy(authController.getAuthenticatedUserName());
        return ResponseEntity.ok(professorService.save(professor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable String id) {
        return ResponseEntity.ok(professorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Professor>> getAllProfessors() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable String id) {
        professorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}