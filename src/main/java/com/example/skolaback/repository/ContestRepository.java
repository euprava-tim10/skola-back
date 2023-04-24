package com.example.skolaback.repository;

import com.example.skolaback.model.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
    @Query("SELECT c FROM Contest c WHERE c.school.id = ?1")
    List<Contest> findContestsBySchoolId(long schoolId);
}
