package com.example.skolaback.service;

import com.example.skolaback.model.dto.application.CreateContestApplicationDTO;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.entity.Student;

public interface StudentService {

    Student getById(long id);
    Student getByJMBG(String jmbg);
    Student createStudent(CreateContestApplicationDTO createContestApplicationDTO, School school);
    Double calculateRangPoints(long studentId);
}
