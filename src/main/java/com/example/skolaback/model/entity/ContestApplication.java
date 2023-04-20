package com.example.skolaback.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ContestApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column
    private Double rangPoints;
}
