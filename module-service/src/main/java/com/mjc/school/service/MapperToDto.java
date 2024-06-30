package com.mjc.school.service;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import org.modelmapper.ModelMapper;

public class MapperToDto {

    private final ModelMapper modelMapper = new ModelMapper();

    public NewsDto convertToDto(NewsModel newsModel) {
        return modelMapper.map(newsModel, NewsDto.class);
    }

}
