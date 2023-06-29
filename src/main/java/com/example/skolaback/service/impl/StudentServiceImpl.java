package com.example.skolaback.service.impl;

import com.example.skolaback.model.dto.application.CreateContestApplicationDTO;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.entity.Student;
import com.example.skolaback.repository.StudentRepository;
import com.example.skolaback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getById(long id) {
        return studentRepository.getById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getByJMBG(String jmbg) {
        return studentRepository.getStudentByJmbg(jmbg);
    }

    @Override
    public Student createStudent(CreateContestApplicationDTO createContestApplicationDTO, School school) {
        Student student = new Student();
        student.setFirstName(createContestApplicationDTO.getChildFirstName());
        student.setLastName(createContestApplicationDTO.getChildLastName());
        student.setJmbg(createContestApplicationDTO.getChildJmbg());

        studentRepository.save(student);
        return student;
    }

    @Override
    public Double calculateRangPoints(long studentId) {
        Student student = getById(studentId);
        double rangPoints = 0.0;
        for (Double value : student.getGpa().values()) {
            rangPoints += value * 5;
        }
        return rangPoints;
    }
}
