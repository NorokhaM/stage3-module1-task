package com.mjc.school.repository.reader;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataReaderImpl implements DataReader{
    @Override
    public List<AuthorModel> readAuthorsData(String path) {
        List<AuthorModel> authors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Long id = Long.parseLong(parts[0]);
                String name = parts[1];
                authors.add(new AuthorModel(id, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<NewsModel> readNewsData(String path) {
        List<NewsModel> news = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Long id = Long.parseLong(parts[0]);
                String title = parts[1];
                String content = parts[2];
                LocalDateTime createDate = LocalDateTime.parse(parts[3]);
                LocalDateTime lastUpdateDate = LocalDateTime.parse(parts[4]);
                Long authorId = Long.parseLong(parts[5]);
                news.add(new NewsModel(id, title, content, createDate, lastUpdateDate, authorId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }

}
