package com.example.skolaback.model.dto.contest;

import com.example.skolaback.model.dto.course.CourseQuotaResponseDTO;
import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.CourseQuota;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.enumerations.ContestStatus;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ContestResponseDTO {
    private long id;
    private SchoolResponseDTO school;
    private Set<CourseQuotaResponseDTO> quotas;
    private Integer primarySchoolQuota;
    private String text;
    private LocalDate startDate;
    private LocalDate endDate;
    private ContestStatus contestStatus;
}
