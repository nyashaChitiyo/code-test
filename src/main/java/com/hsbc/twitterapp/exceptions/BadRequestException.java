package com.hsbc.twitterapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException  extends RuntimeException implements Supplier<BadRequestException> {

    public BadRequestException(String message){
        super(message);
    }

    @Override
    public BadRequestException get() {
        return null;
    }
}