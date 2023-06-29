package com.example.skolaback.model.dto.diploma;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiplomaCreateDTO {
    private String jmbg;
    private Map<Integer, Double> gpa;
    private String profession;
}
