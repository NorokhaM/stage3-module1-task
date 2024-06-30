package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exceptions.ContentLengthException;
import com.mjc.school.service.exceptions.InvalidDataFormatException;
import com.mjc.school.service.exceptions.TitleLengthException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Validator implements Validate{

    private static volatile Validator instance=null;

    public static Validator getInstance(){
        if(instance==null){
            synchronized (Validator.class){
                if(instance==null){
                    instance=new Validator();
                }
            }
        }
        return instance;
    }

    private Validator(){

    }

    @Override
    public boolean validate(NewsDto newsDto) throws TitleLengthException, ContentLengthException, InvalidDataFormatException {
        if (newsDto.getTitle().length() <= 5 || newsDto.getTitle().length() > 30) {
            throw new TitleLengthException("Title length must be between 5 and 30 characters");
        } else if (newsDto.getContent().length() <= 5 || newsDto.getContent().length() > 255) {
            throw new ContentLengthException("Content length must be between 5 and 255 characters");
        } else if (!isValidISO8601Date(newsDto.getCreateDate()) || !isValidISO8601Date(newsDto.getLastUpdateDate())) {
            throw new InvalidDataFormatException("Dates must be in ISO 8601 format");
        } else {
            return true;
        }
    }

    private boolean isValidISO8601Date(LocalDateTime date) {
        try {
            DateTimeFormatter.ISO_DATE_TIME.format(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

}
