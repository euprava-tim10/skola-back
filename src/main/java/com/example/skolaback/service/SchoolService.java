package com.example.skolaback.service;

import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.Contest;
import com.example.skolaback.model.entity.Course;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.entity.Student;

import java.util.List;

public interface SchoolService {

    School getById(long id);
    List<Contest> getContestsBySchool(long id);
    List<Course> getCoursesBySchool(long id);

    List<Student> getStudents(long id);

}
