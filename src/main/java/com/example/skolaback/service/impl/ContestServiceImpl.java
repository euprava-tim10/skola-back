package com.example.skolaback.service.impl;

import com.example.skolaback.model.entity.Contest;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.enumerations.SchoolType;
import com.example.skolaback.repository.ContestRepository;
import com.example.skolaback.repository.CourseQuotaRepository;
import com.example.skolaback.service.ContestService;
import com.example.skolaback.service.SchoolService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;

    private final CourseQuotaRepository courseQuotaRepository;

    private final SchoolService schoolService;

    public ContestServiceImpl(SchoolService schoolService, CourseQuotaRepository courseQuotaRepository, ContestRepository contestRepository) {
        this.schoolService = schoolService;
        this.courseQuotaRepository = courseQuotaRepository;
        this.contestRepository = contestRepository;
    }


    @Override
    public long createContest(long schoolId, Contest contest) {
        School school = schoolService.getById(schoolId);

        contest.setStartDate(LocalDate.now());
        contest.setSchool(school);

        if (school.getType().equals(SchoolType.SREDNJA) && !contest.getQuotas().isEmpty()) {
            contest.setPrimarySchoolQuota(null);
            courseQuotaRepository.saveAll(contest.getQuotas());
        }

        contestRepository.save(contest);

        return contest.getId();
    }
}
