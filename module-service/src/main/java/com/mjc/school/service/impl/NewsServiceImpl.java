package com.mjc.school.service.impl;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.source.DataSource;
import com.mjc.school.service.MapperToDto;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exceptions.ContentLengthException;
import com.mjc.school.service.exceptions.InvalidDataFormatException;
import com.mjc.school.service.exceptions.TitleLengthException;
import com.mjc.school.service.validator.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final MapperToDto mapper;
    private final DataSource dataSource;
    private final Validator validator;

    public NewsServiceImpl(MapperToDto mapper, DataSource dataSource, Validator validator) {
        this.mapper = mapper;
        this.dataSource = dataSource;
        this.validator = validator;
    }

    @Override
    public List<NewsDto> getAllNews() {
        return dataSource.readAllNews()
                .stream()
                .map(mapper::convertToDto)
                .toList();
    }

    @Override
    public NewsDto getNewsById(Long id) {
        try{
            return mapper.convertToDto(dataSource.readNewsById(id));
        } catch (IllegalArgumentException e){
            System.out.println("News with id "+id+" not found");
            return null;
        }
    }

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        LocalDateTime date=LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        try{
            validator.validate(newsDto);
            return mapper.convertToDto(dataSource.createNews(new NewsModel(getId(dataSource), newsDto.getTitle(), newsDto.getContent(), date, date, newsDto.getAuthorId())));
        } catch(ContentLengthException | InvalidDataFormatException | TitleLengthException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        LocalDateTime date=LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        try{
            validator.validate(newsDto);
            return mapper.convertToDto(dataSource.updateNews(new NewsModel(id, newsDto.getTitle(), newsDto.getContent(), dataSource.readNewsById(id).getCreateDate(), date, newsDto.getAuthorId())));
        } catch(ContentLengthException | InvalidDataFormatException | TitleLengthException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteNews(Long id) {
        dataSource.deleteNewsById(id);
    }


    private Long getId(DataSource dataSource) {
        return (long) (dataSource.readAllNews().size() + 1);
    }
}
