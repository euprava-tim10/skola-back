package com.example.skolaback.model.entity;

import lombok.Data;
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
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    private String firstName;
    private String lastName;
    private String jmbg;
    private String course;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    @ElementCollection
    @CollectionTable(name = "student_gpa_mapping",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "grade_year")
    @Column(name = "gpa")
    private Map<Integer, Double> gpa;
}
