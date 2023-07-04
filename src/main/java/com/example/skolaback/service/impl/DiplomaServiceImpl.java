package com.example.skolaback.service.impl;

import com.example.skolaback.model.dto.diploma.DiplomaCreateDTO;
import com.example.skolaback.model.entity.Diploma;
import com.example.skolaback.model.entity.Student;
import com.example.skolaback.model.enumerations.Profession;
import com.example.skolaback.model.enumerations.SchoolType;
import com.example.skolaback.repository.DiplomaRepository;
import com.example.skolaback.service.DiplomaService;
import com.example.skolaback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class DiplomaServiceImpl implements DiplomaService {

    private final DiplomaRepository diplomaRepository;
    private final StudentService studentService;

    public DiplomaServiceImpl(DiplomaRepository diplomaRepository, StudentService studentService) {
        this.diplomaRepository = diplomaRepository;
        this.studentService = studentService;
    }

    @Override
    public boolean checkExistDiploma(String studentJmbg, SchoolType schoolType) {
        Optional<Diploma> diploma = diplomaRepository.getDiploma(studentJmbg, schoolType);
        return diploma.isPresent();
    }

    @Override
    public Diploma getDiploma(String studentJmbg, SchoolType schoolType) {
        Optional<Diploma> diploma = diplomaRepository.getDiploma(studentJmbg, schoolType);
        return diploma.orElse(null);
    }

    @Override
    public void createDiploma(DiplomaCreateDTO diplomaCreateDTO) {
        Student student = studentService.getByJMBG(diplomaCreateDTO.getJmbg());

        Diploma diploma = new Diploma();
        diploma.setStudent(student);
        diploma.setSchool(student.getSchool());
        diploma.setGpa(diplomaCreateDTO.getGpa());
        diploma.setDate(LocalDate.now());
        diploma.setProfession(Profession.valueOf(diplomaCreateDTO.getProfession()));

        if (student.getSchool().getType() == SchoolType.OSNOVNA) {
            diploma.setCourse(null);
        }

        if (student.getSchool().getType() == SchoolType.SREDNJA) {
            diploma.setCourse(student.getCourse());
        }
        student.setGpa(diplomaCreateDTO.getGpa());
        studentService.save(student);
        diplomaRepository.save(diploma);
    }
}
