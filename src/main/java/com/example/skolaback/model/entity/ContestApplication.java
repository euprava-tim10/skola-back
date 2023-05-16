package com.example.skolaback.model.entity;

import com.example.skolaback.model.enumerations.ApplicationStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    private Course firstWish;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}
