package com.example.skolaback.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ContestApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column
    private Double rangPoints;
}
