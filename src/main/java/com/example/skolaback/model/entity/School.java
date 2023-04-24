package com.example.skolaback.model.entity;

import com.example.skolaback.model.enumerations.SchoolType;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    private String name;
    private String place;
    @Enumerated(EnumType.STRING)
    private SchoolType type;
//    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
//    private Set<Student> students = new HashSet<>();
}
