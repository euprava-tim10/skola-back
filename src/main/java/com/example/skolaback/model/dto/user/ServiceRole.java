package com.example.skolaback.model.dto.user;

import com.example.skolaback.model.mapper.HashMapConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.Map;

@Getter
@Setter
public class ServiceRole {

    private long id;

    private String service;

    private String role;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> attributes;
}
