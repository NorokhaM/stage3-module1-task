package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exceptions.ContentLengthException;
import com.mjc.school.service.exceptions.InvalidDataFormatException;
import com.mjc.school.service.exceptions.TitleLengthException;

public interface Validate {
    boolean validate(NewsDto newsDto) throws TitleLengthException, ContentLengthException, InvalidDataFormatException;
}
