package com.mjc.school.repository.source;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.util.List;

public interface DataSource {
    List<AuthorModel> readAllAuthors();

    List<NewsModel> readAllNews();

    NewsModel readNewsById(Long id);

    NewsModel createNews(NewsModel news);

    NewsModel updateNews(NewsModel news);

    void deleteNewsById(Long id);
}
