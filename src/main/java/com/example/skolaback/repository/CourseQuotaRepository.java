package com.example.skolaback.repository;

import com.example.skolaback.model.entity.CourseQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseQuotaRepository extends JpaRepository<CourseQuota, Long> {
}
