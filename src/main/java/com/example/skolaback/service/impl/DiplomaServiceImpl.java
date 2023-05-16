package com.example.skolaback.service.impl;

import com.example.skolaback.model.entity.Diploma;
import com.example.skolaback.model.enumerations.SchoolType;
import com.example.skolaback.repository.DiplomaRepository;
import com.example.skolaback.service.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiplomaServiceImpl implements DiplomaService {

    private final DiplomaRepository diplomaRepository;

    public DiplomaServiceImpl(DiplomaRepository diplomaRepository) {
        this.diplomaRepository = diplomaRepository;
    }

    @Override
    public boolean checkExistDiploma(long studentId, SchoolType schoolType) {
        Optional<Diploma> diploma = diplomaRepository.getDiploma(studentId, schoolType);
        return diploma.isPresent();
    }
}
