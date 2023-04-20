package com.example.skolaback.controller;

import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skole")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/{id}")
    public SchoolResponseDTO getSchool(@PathVariable long id) {
        return schoolService.getById(id);
    }
}
