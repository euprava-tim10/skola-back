package com.example.skolaback.model.mapper;

import com.example.skolaback.model.dto.diploma.DiplomaResponseDTO;
import com.example.skolaback.model.entity.Diploma;

public class DiplomaMapper {
    public static DiplomaResponseDTO mapDiploma(Diploma diploma){
        return DiplomaResponseDTO.builder()
                .firstName(diploma.getStudent().getFirstName())
                .lastName(diploma.getStudent().getLastName())
                .jmbg(diploma.getStudent().getJmbg())
                .schoolName(diploma.getSchool().getName())
                .schoolType(diploma.getSchool().getType().toString())
                .course(diploma.getCourse())
                .profession(diploma.getProfession().toString())
                .gpa(diploma.getGpa())
                .date(diploma.getDate())
                .build();
    }
}
