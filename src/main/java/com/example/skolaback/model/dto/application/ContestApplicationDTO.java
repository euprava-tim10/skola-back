package com.example.skolaback.model.dto.application;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.course.CourseResponseDTO;
import com.example.skolaback.model.enumerations.ApplicationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContestApplicationDTO {
    private long id;
    private ContestResponseDTO contest;
    private Double rangPoints;
    private CourseResponseDTO firstWish;
    private ApplicationStatus applicationStatus;
}
