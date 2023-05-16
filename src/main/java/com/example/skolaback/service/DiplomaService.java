package com.example.skolaback.service;

import com.example.skolaback.model.entity.Diploma;
import com.example.skolaback.model.enumerations.SchoolType;

public interface DiplomaService {
    boolean checkExistDiploma(String studentJmbg, SchoolType schoolType);

    Diploma getDiploma(String studentJmbg, SchoolType schoolType);
}
