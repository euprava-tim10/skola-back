package com.example.skolaback.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private School school;
}
