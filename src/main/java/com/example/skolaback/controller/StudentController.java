package com.example.skolaback.controller;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.diploma.DiplomaResponseDTO;
import com.example.skolaback.model.entity.Diploma;
import com.example.skolaback.model.entity.Student;
import com.example.skolaback.model.enumerations.ContestStatus;
import com.example.skolaback.model.enumerations.SchoolType;
import com.example.skolaback.model.mapper.DiplomaMapper;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.security.permission.IsLoggedIn;
import com.example.skolaback.service.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ucenici")
public class StudentController {

    private final DiplomaService diplomaService;

    public StudentController(DiplomaService diplomaService) {
        this.diplomaService = diplomaService;
    }

    @GetMapping("/{jmbg}/diploma/{tipSkole}")
    public ResponseEntity<DiplomaResponseDTO> getStudentDiploma(@PathVariable String jmbg,
                                                               @PathVariable SchoolType tipSkole) {
        try {
            return new ResponseEntity<>
                    (DiplomaMapper.mapDiploma(diplomaService.getDiploma(jmbg, tipSkole)), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
