package com.example.skolaback.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    @Column
    private String course;
    @ElementCollection
    @CollectionTable(name = "diploma_gpa_mapping",
            joinColumns = {@JoinColumn(name = "diploma_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "grade_year")
    @Column(name = "gpa")
    private Map<Integer, Double> gpa;
    @Column
    private LocalDate date;
}
