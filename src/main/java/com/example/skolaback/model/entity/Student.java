package com.example.skolaback.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    @Column
    private String firstName;
    private String lastName;
    private String password;
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
    private Map<String, Double> gpa;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<ContestApplication> applications = new HashSet<>();
}
