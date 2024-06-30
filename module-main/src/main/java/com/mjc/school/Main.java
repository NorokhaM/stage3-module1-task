package com.mjc.school;

import com.mjc.school.controller.RequestController;
import com.mjc.school.repository.impl.DataSourceImpl;
import com.mjc.school.service.MapperToDto;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.service.validator.Validator;


import java.util.Scanner;

public class Main {
    private static final String authorPath= "author.txt";
    private static final String newsPath= "content.txt";

    public static void main(String[] args) {
        int operation=0;
        Scanner scanner = new Scanner(System.in);
        NewsService newsService = new NewsServiceImpl(new MapperToDto(), new DataSourceImpl(authorPath, newsPath), Validator.getInstance());
        RequestController requestController = new RequestController(newsService);

        do{
            System.out.println("Choose operation:\n1. Get all news\n2. Get news by id\n3. Create news\n4. Update news\n5. Delete news\n0. Exit");
            operation = scanner.nextInt();
            switch (operation){
                case 1 -> requestController.getAllNews().forEach(System.out::println);
                case 2 -> {
                    System.out.println("Enter news id:");
                    System.out.println(requestController.getNewsById(scanner.nextLong()));
                }
                case 3 ->{
                    System.out.println("Enter news title:");
                    String title = scanner.next();
                    System.out.println("Enter news content:");
                    String content = scanner.next();
                    System.out.println("Enter news author id:");
                    Long authorId = scanner.nextLong();
                    System.out.println(requestController.createNews(new NewsDto(title, content, authorId)));
                }
                case 4 ->{
                    System.out.println("Enter news id:");
                    Long id = scanner.nextLong();
                    System.out.println("Enter news title:");
                    String title = scanner.next();
                    System.out.println("Enter news content:");
                    String content = scanner.next();
                    System.out.println("Enter news author id:");
                    Long authorId = scanner.nextLong();
                    System.out.println(requestController.updateNews(id,new NewsDto(title, content, authorId)));
                }
                case 5 ->{
                    System.out.println("Enter news id:");
                    requestController.deleteNews(scanner.nextLong());
                }

                case 0 ->{
                    System.out.println("Goodbye!");
                    scanner.close();
                }

            }
        } while(operation!=0);

    }
}
