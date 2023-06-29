package com.example.skolaback.model.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStudentRoleDTO {
    private long id;
    private String service;
    private String role;
    private Map<String, Object> attributes;
}
