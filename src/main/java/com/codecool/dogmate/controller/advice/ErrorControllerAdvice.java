package com.codecool.dogmate.controller.advice;

import com.codecool.dogmate.controller.advice.Exceptions.ProvincesNotFoundException;
import com.codecool.dogmate.controller.advice.Exceptions.VoivodeshipsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value ={VoivodeshipsNotFoundException.class, ProvincesNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoDeveloper(RuntimeException exception) {
        return  new ErrorResponse(exception.getMessage());
    }

    private record ErrorResponse(String errorMessage) {

    }
}
