package com.example.skolaback.model.dto.student;

import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.ContestApplication;
import com.example.skolaback.model.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String jmbg;
    private String course;
    private SchoolResponseDTO school;
    private Map<Integer, Double> gpa;
}
