package com.example.skolaback.service.impl;

import com.example.skolaback.model.entity.Course;
import com.example.skolaback.repository.CourseRepository;
import com.example.skolaback.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getById(long courseId) {
        return courseRepository.getById(courseId);
    }
}
