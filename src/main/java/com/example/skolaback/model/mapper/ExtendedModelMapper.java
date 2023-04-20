package com.example.skolaback.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExtendedModelMapper extends ModelMapper {

    public <S, T> List<T> mapAll(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> map(element, targetClass)).toList();
    }

    public <S, T> Page<T> mapAll(Page<S> source, Class<T> targetClass) {
        return source.map(objectEntity -> map(objectEntity, targetClass));
    }

}
