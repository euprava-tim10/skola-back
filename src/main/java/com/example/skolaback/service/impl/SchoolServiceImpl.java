package com.example.skolaback.service.impl;

import com.example.skolaback.model.entity.Contest;
import com.example.skolaback.model.entity.Course;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.entity.Student;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.repository.ContestRepository;
import com.example.skolaback.repository.CourseRepository;
import com.example.skolaback.repository.SchoolRepository;
import com.example.skolaback.repository.StudentRepository;
import com.example.skolaback.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ContestRepository contestRepository;
    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, ContestRepository contestRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.contestRepository = contestRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public School getById(long id) {
        return schoolRepository.getById(id);
    }

    @Override
    public List<Contest> getContestsBySchool(long id) {
        return contestRepository.findContestsBySchoolId(id);
    }

    @Override
    public List<Course> getCoursesBySchool(long id) {
        return courseRepository.findCoursesBySchool(id);
    }

    @Override
    public List<Student> getStudents(long id) {
        return studentRepository.getStudentsBySchoolId(id);
    }
}
