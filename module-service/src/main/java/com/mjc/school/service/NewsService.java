package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews();

    NewsDto getNewsById(Long id);

    NewsDto createNews(NewsDto newsDto);

    NewsDto updateNews(Long id, NewsDto newsDto);

    void deleteNews(Long id);
}
