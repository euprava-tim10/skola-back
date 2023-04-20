package com.example.skolaback.service.impl;

import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.School;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.model.mapper.SchoolMapper;
import com.example.skolaback.repository.SchoolRepository;
import com.example.skolaback.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final ExtendedModelMapper modelMapper;
    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, ExtendedModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SchoolResponseDTO getById(long id) {
        return modelMapper.map(schoolRepository.getById(id), SchoolResponseDTO.class);
    }
}
