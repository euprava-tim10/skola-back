package com.example.skolaback.service;

import com.example.skolaback.model.entity.Contest;

public interface ContestService {
    long createContest(long schoolId, Contest contest);
}
