package com.mjc.school.controller;

import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;

import java.util.List;

public class RequestController implements NewsOperations{

    private final NewsService newsService;

    public RequestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public List<NewsDto> getAllNews() {
        return newsService.getAllNews();
    }

    @Override
    public NewsDto getNewsById(Long id) {
        return newsService.getNewsById(id);
    }

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        return newsService.createNews(newsDto);
    }

    @Override
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        return newsService.updateNews(id, newsDto);
    }

    @Override
    public void deleteNews(Long id) {
        newsService.deleteNews(id);
    }

}
