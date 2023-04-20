package com.example.skolaback.model.dto.school;

import com.example.skolaback.model.enumerations.SchoolType;
import lombok.*;


@Data
@Builder
public class SchoolResponseDTO {
    private String name;
    private String place;
    private SchoolType type;
}
