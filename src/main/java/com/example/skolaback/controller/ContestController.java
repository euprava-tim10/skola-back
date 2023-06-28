package com.example.skolaback.controller;

import com.example.skolaback.model.dto.application.CreateContestApplicationDTO;
import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.ContestApplication;
import com.example.skolaback.model.enumerations.ContestStatus;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.security.permission.IsAdmin;
import com.example.skolaback.security.permission.IsLoggedIn;
import com.example.skolaback.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/konkursi")
public class ContestController {

    private final ExtendedModelMapper modelMapper;
    private final ContestService contestService;

    public ContestController(ContestService contestService, ExtendedModelMapper modelMapper) {
        this.contestService = contestService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @IsLoggedIn
    public List<ContestResponseDTO> getActiveContests() {
        return modelMapper.mapAll(contestService.getContestByStatus(ContestStatus.AKTIVAN), ContestResponseDTO.class);
    }

    @GetMapping("/{id}")
    @IsLoggedIn
    public ContestResponseDTO getContest(@PathVariable long id) {
        return modelMapper.map(contestService.getById(id), ContestResponseDTO.class);
    }

    @PostMapping("/{id}/prijave")
    @IsLoggedIn
    public long createApplication(@PathVariable long id,
                                                @RequestBody CreateContestApplicationDTO contestApplication) {
        return contestService.createApplication(id, contestApplication);
    }

    @GetMapping("/{id}/smerovi/{smerId}/prijave")
    @IsLoggedIn
    public List<ContestApplication> getContestApplicationByCourse(
            @PathVariable long id,
            @PathVariable long smerId
    ) {
        return contestService.getContestApplicationByCourse(id, smerId);
    }

    @GetMapping("/{id}/prijave")
    @IsLoggedIn
    public List<ContestApplication> getContestApplication(
            @PathVariable long id
    ) {
        return contestService.getContestApplication(id);
    }
}
