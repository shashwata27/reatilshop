package com.pet.project.retailshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourseAlredyExistsException extends RuntimeException {
    public ResourseAlredyExistsException(List<Integer> ids) {
        super(MessageFormat.format("Resource with ids: {0} already exists.",ids));
    }
}
