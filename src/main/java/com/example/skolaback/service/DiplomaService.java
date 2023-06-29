package com.example.skolaback.service;

import com.example.skolaback.model.dto.diploma.DiplomaCreateDTO;
import com.example.skolaback.model.entity.Diploma;
import com.example.skolaback.model.enumerations.SchoolType;

public interface DiplomaService {
    boolean checkExistDiploma(String studentJmbg, SchoolType schoolType);

    Diploma getDiploma(String studentJmbg, SchoolType schoolType);
    void createDiploma(DiplomaCreateDTO diplomaCreateDTO);
}
