import com.mjc.school.repository.impl.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.source.DataSource;
import com.mjc.school.service.MapperToDto;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NewsServiceTest {
    private NewsService newsService;
    private NewsDto newsDto;
    private MapperToDto mapperToDto;
    private DataSource dataSource;

    @BeforeEach
    public void setUp() {
        List<AuthorModel> authors = new ArrayList<>();
        List<NewsModel> news = new ArrayList<>();

        authors.add(new AuthorModel(1L, "Author1"));
        authors.add(new AuthorModel(2L, "Author2"));
        authors.add(new AuthorModel(3L, "Author3"));
        authors.add(new AuthorModel(4L, "Author4"));

        news.add(new NewsModel(1L, "title1", "content1", LocalDateTime.parse("2023-10-12T21:41:39"), LocalDateTime.parse("2023-10-12T21:41:39"), 2L));
        news.add(new NewsModel(2L, "title2", "content2", LocalDateTime.parse("2023-11-12T21:41:39"), LocalDateTime.parse("2023-11-12T21:41:39"), 3L));
        news.add(new NewsModel(3L, "title3", "content3", LocalDateTime.parse("2023-12-12T21:41:39"), LocalDateTime.parse("2023-12-12T21:41:39"), 4L));
        news.add(new NewsModel(4L, "title4", "content4", LocalDateTime.parse("2022-10-12T21:41:39"), LocalDateTime.parse("2022-10-12T21:41:39"), 1L));
        news.add(new NewsModel(5L, "title5", "content5", LocalDateTime.parse("2021-10-12T21:41:39"), LocalDateTime.parse("2021-10-12T21:41:39"), 3L));
        mapperToDto = new MapperToDto();
        dataSource= new Repository(authors, news);
        newsService = new NewsServiceImpl(mapperToDto, dataSource, Validator.getInstance());
    }


    @Test
    public void testGetAllNews() {
        List<NewsDto> newsDtoExpected = dataSource.readAllNews().stream().map(mapperToDto::convertToDto).toList();
        List<NewsDto> newsDtoActual = newsService.getAllNews();
        assertEquals(newsDtoExpected.toString(), newsDtoActual.toString());
    }

    @Test
    public void testGetNewsByValidId() {
        NewsDto newsDtoExpected = mapperToDto.convertToDto(dataSource.readAllNews().get(0));
        NewsDto newsDtoActual = newsService.getNewsById(1L);
        assertEquals(newsDtoExpected.toString(), newsDtoActual.toString());
    }

    @Test
    public void testGetNewsByInvalidId() {
        assertNull(newsService.getNewsById(10L));
    }

    @Test
    public void testCreateNews(){
        NewsDto newsDtoExpected = new NewsDto("title6", "content6", 2L);
        NewsDto newsDtoActual = newsService.createNews(newsDtoExpected);
        assertEquals(6, newsDtoActual.getId());
    }

    @Test
    public void testUpdateNews(){
        NewsDto newsDtoExpected = new NewsDto("title1", "content1", 2L);
        NewsDto newsDtoActual = newsService.updateNews(1L, newsDtoExpected);
        assertEquals(newsDtoExpected.getTitle(), newsDtoActual.getTitle());
        assertEquals(newsDtoExpected.getContent(), newsDtoActual.getContent());
        assertEquals(newsDtoExpected.getAuthorId(), newsDtoActual.getAuthorId());
    }



    @Test
    public void testDeleteNewsWithValidId(){
        newsService.deleteNews(1L);
        assertNull(newsService.getNewsById(1L));
    }

    @Test
    public void testDeleteNewsWithInvalidId(){
        newsService.deleteNews(10L);
        assertNull(newsService.getNewsById(10L));
    }
}
