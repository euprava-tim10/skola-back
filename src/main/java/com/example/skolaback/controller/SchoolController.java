package com.example.skolaback.controller;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.course.CourseResponseDTO;
import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.Contest;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.security.permission.IsAdmin;
import com.example.skolaback.service.ContestService;
import com.example.skolaback.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skole")
public class SchoolController {
    private final ExtendedModelMapper modelMapper;
    private final ContestService contestService;
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService, ContestService contestService, ExtendedModelMapper modelMapper) {
        this.schoolService = schoolService;
        this.contestService = contestService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public SchoolResponseDTO getSchool(@PathVariable long id) {
        return modelMapper.map(schoolService.getById(id), SchoolResponseDTO.class);
    }


    @PostMapping("/{id}/konkursi")
    @IsAdmin
    public ContestResponseDTO createContest(@PathVariable long id, @RequestBody Contest contest) {
        return contestService.createContest(id, contest);
    }

    @GetMapping("/{id}/konkursi")
    public List<ContestResponseDTO> getSchoolContests(@PathVariable long id) {
        return modelMapper.mapAll(schoolService.getContestsBySchool(id), ContestResponseDTO.class);
    }

    @GetMapping("/{id}/smerovi")
    public List<CourseResponseDTO> getSchoolCourses(@PathVariable long id) {
        return modelMapper.mapAll(schoolService.getCoursesBySchool(id), CourseResponseDTO.class);
    }
}
