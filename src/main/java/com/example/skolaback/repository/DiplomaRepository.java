package com.example.skolaback.repository;

import com.example.skolaback.model.entity.Diploma;
import com.example.skolaback.model.enumerations.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Long> {

    @Query("SELECT d FROM Diploma d WHERE d.student.id = ?1 AND d.school.type = ?2")
    Optional<Diploma> getDiploma(long studentId, SchoolType type);
}
