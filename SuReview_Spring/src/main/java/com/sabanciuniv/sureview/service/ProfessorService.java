package com.sabanciuniv.sureview.service;

import com.sabanciuniv.sureview.model.Professor;
import com.sabanciuniv.sureview.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService implements GenericService<Professor> {

    private final ProfessorRepository professorRepository;

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor findById(String id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    @Override
    public Iterable<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        professorRepository.deleteById(id);
    }
}
