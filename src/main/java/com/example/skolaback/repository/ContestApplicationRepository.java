package com.example.skolaback.repository;

import com.example.skolaback.model.entity.ContestApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContestApplicationRepository extends JpaRepository<ContestApplication, Long> {


    Optional<ContestApplication> getByStudentJmbgAndContestId(String studentJmbg, long contestId);
}
