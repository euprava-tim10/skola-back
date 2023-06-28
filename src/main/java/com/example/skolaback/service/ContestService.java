package com.example.skolaback.service;

import com.example.skolaback.model.dto.application.CreateContestApplicationDTO;
import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.entity.Contest;
import com.example.skolaback.model.entity.ContestApplication;
import com.example.skolaback.model.enumerations.ContestStatus;

import java.util.List;

public interface ContestService {

    Contest getById(long id);
    ContestResponseDTO createContest(long schoolId, Contest contest);
    long createApplication(long contestId, CreateContestApplicationDTO createContestApplicationDTO);
    boolean checkIfApplicationExist(Contest contest, String studentJmbg);

    List<Contest> getContestByStatus(ContestStatus contestStatus);
    List<ContestApplication> getContestApplicationByCourse(long contestId, long courseId);
    List<ContestApplication> getContestApplication(long contestId);
}
