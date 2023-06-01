package com.example.skolaback.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<ServiceRole> serviceRoles;
    private String fatherJmbg;
    private String motherJmbg;
}
