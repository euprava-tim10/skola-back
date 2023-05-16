package com.example.skolaback.model.dto.diploma;

import com.example.skolaback.model.enumerations.SchoolType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Builder
public class DiplomaResponseDTO {

    private String firstName;
    private String lastName;
    private String jmbg;
    private String schoolName;
    private String schoolType;
    private String course;
    private Map<Integer, Double> gpa;
    private LocalDate date;
}
