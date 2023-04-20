package com.example.skolaback.model.mapper;

import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.School;

public class SchoolMapper {

    public static SchoolResponseDTO mapSchoolResponseDTO(School school) {
        return SchoolResponseDTO.builder()
                .name(school.getName())
                .place(school.getPlace())
                .type(school.getType())
                .build();
    }
}
