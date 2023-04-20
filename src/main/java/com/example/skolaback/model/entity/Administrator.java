package com.example.skolaback.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    private String firstName;
    private String lastName;
    private String password;
    private String jmbg;
    @OneToOne
    @JoinColumn(name = "school_id")
    private School school;
}
