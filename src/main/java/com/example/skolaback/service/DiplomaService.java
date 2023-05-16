package com.example.skolaback.service;

import com.example.skolaback.model.enumerations.SchoolType;

public interface DiplomaService {
    boolean checkExistDiploma(long studentId, SchoolType schoolType);
}
