package com.hrd.homework003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(UserNotFoundExceptionHandler.class)
    public ProblemDetail handleException(UserNotFoundExceptionHandler exception) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
                detail.setType(URI.create("about:blank"));
                detail.setProperty("timestemp", LocalDateTime.now());
        return detail;
    }

}
