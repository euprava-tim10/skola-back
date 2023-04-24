package com.example.skolaback.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CourseQuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column
    private Integer quota;

    @ManyToOne
    private Course course;
}
