package com.example.skolaback.controller;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.security.permission.IsAdmin;
import com.example.skolaback.security.permission.IsLoggedIn;
import com.example.skolaback.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/konkursi")
public class ContestController {

    private final ExtendedModelMapper modelMapper;
    private final ContestService contestService;

    public ContestController(ContestService contestService, ExtendedModelMapper modelMapper) {
        this.contestService = contestService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @IsLoggedIn
    public ContestResponseDTO getContest(@PathVariable long id) {
        return modelMapper.map(contestService.getById(id), ContestResponseDTO.class);
    }
}
