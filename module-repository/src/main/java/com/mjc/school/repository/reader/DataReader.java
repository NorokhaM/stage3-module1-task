package com.mjc.school.repository.reader;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.util.List;

public interface DataReader {
    List<AuthorModel> readAuthorsData(String path);
    List<NewsModel> readNewsData(String path);
}
