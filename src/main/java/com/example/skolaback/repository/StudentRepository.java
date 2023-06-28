package com.example.skolaback.repository;

import com.example.skolaback.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByJmbg(String jmbg);
    List<Student> getStudentsBySchoolId(long schoolId);
}
