package com.sabanciuniv.sureview.repository;

import com.sabanciuniv.sureview.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
    // Additional custom methods can be defined here
}
