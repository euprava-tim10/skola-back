package com.example.skolaback.repository;

import com.example.skolaback.model.entity.ContestApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContestApplicationRepository extends JpaRepository<ContestApplication, Long> {


    Optional<ContestApplication> getByStudentJmbgAndContestId(String studentJmbg, long contestId);
    @Query(value = "SELECT a FROM ContestApplication a " +
            "WHERE a.contest.id = ?1 " +
            "AND a.firstWish.id = ?2 " +
            "ORDER BY a.rangPoints DESC")
    List<ContestApplication> getContestApplicationsByCourse(long contestId, long courseId);

    @Query(value = "SELECT a.* FROM contest_application a " +
            "WHERE a.contest_id = ?1 " +
            "AND a.first_wish_id = ?2 " +
            "ORDER BY a.rang_oints DESC " +
            "LIMIT ?3", nativeQuery = true)
    List<ContestApplication> getTopNContestApplicationsByCourse(long contestId, long courseId, long n);

    @Query(value = "SELECT a FROM ContestApplication a " +
            "WHERE a.contest.id = ?1 " +
            "ORDER BY a.rangPoints DESC")
    List<ContestApplication> getContestApplications(long contestId);
}
