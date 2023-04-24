package com.example.skolaback.model.dto.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CourseQuotaResponseDTO {

    private Integer quota;
    private CourseResponseDTO course;
}
