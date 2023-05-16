package com.example.skolaback.model.dto.application;

import com.example.skolaback.model.dto.course.CourseResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateContestApplicationDTO {
    private String childFirstName;
    private String childLastName;
    private String childJmbg;
    private CourseResponseDTO firstWish;
}
