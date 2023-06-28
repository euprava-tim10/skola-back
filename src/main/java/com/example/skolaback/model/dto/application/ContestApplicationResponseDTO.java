package com.example.skolaback.model.dto.application;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.student.StudentResponseDTO;
import com.example.skolaback.model.entity.Course;
import com.example.skolaback.model.enumerations.ApplicationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ContestApplicationResponseDTO {

    private long id;
    private ContestResponseDTO contest;
    private StudentResponseDTO student;
    private Double rangPoints;
    private Course firstWish;
    private ApplicationStatus applicationStatus;
}
