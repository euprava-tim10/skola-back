package com.example.skolaback.model.entity;

import com.example.skolaback.model.enumerations.ContestStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<CourseQuota> quotas = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private ContestStatus contestStatus;

    @Column
    private Integer primarySchoolQuota;
    private String text;
    private LocalDate startDate;
    private LocalDate endDate;

}
