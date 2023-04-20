package com.example.skolaback.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    @Column
    private String firstName;
    private String lastName;
    private String password;
    private String jmbg;
}
