package com.example.skolaback.service;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.entity.Contest;

public interface ContestService {

    Contest getById(long id);
    ContestResponseDTO createContest(long schoolId, Contest contest);
}
