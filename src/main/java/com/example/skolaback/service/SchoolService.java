package com.example.skolaback.service;

import com.example.skolaback.model.dto.school.SchoolResponseDTO;

public interface SchoolService {

    SchoolResponseDTO getById(long id);
}
