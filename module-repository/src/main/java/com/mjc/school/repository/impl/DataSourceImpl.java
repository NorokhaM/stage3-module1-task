package com.mjc.school.repository.impl;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.reader.DataReader;
import com.mjc.school.repository.reader.DataReaderImpl;
import com.mjc.school.repository.source.DataSource;

import java.util.List;

public class DataSourceImpl implements DataSource    {
    private List<AuthorModel> authors;
    private List<NewsModel> newsSourse;
    private DataReader dataReader;

    public DataSourceImpl(List<AuthorModel> authors, List<NewsModel> newsSourse) {
        this.authors = authors;
        this.newsSourse = newsSourse;
    }

    public DataSourceImpl(String authorsPath, String newsPath) {
        dataReader=new DataReaderImpl();
        authors = dataReader.readAuthorsData(authorsPath);
        newsSourse = dataReader.readNewsData(newsPath);
    }

    @Override
    public List<AuthorModel> readAllAuthors() {
        return authors;
    }

    @Override
    public List<NewsModel> readAllNews() {
        return newsSourse;
    }

    @Override
    public NewsModel readById(Long id) {
        for (NewsModel news : newsSourse) {
            if (news.getId().equals(id)) {
                return news;
            }
        }
        return null;
    }

    @Override
    public NewsModel createNews(NewsModel news) {
        Long maxId = 0L;
        for (NewsModel newsModel : newsSourse) {
            if (newsModel.getId() > maxId) {
                maxId = newsModel.getId();
            }
        }
        news.setId(maxId + 1);
        newsSourse.add(news);
        return news;
    }

    @Override
    public NewsModel updateNews(NewsModel news) {
        Long id=news.getId();
        for (NewsModel newsModel : newsSourse) {
            if (newsModel.getId().equals(id)) {
                newsSourse.set(newsSourse.indexOf(newsModel), news);
                return news;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteNewsById(Long id) {
        for (NewsModel newsModel : newsSourse) {
            if (newsModel.getId().equals(id)) {
                newsSourse.remove(newsModel);
                return true;
            }
        }
        return false;
    }

}
