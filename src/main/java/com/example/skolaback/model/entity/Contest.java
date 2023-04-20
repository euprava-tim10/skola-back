package com.example.skolaback.model.entity;

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

    @Column
    private String course;
    private Integer quota;
    private LocalDate year;

    @OneToMany(mappedBy = "contest", fetch = FetchType.EAGER)
    private Set<ContestApplication> applications = new HashSet<>();

}