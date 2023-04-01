package com.codecool.dogmate.controller.advice;

import com.codecool.dogmate.controller.advice.Exceptions.ProvincesNotFoundException;
import com.codecool.dogmate.controller.advice.Exceptions.VoivodeshipsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value ={VoivodeshipsNotFoundException.class, ProvincesNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoDeveloper(RuntimeException exception) {
        return  new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBadRequest(MethodArgumentNotValidException ex) {
        String errMsg = ex.getAllErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        return new ErrorResponse(errMsg);
    }

    private record ErrorResponse(String errorMessage) {
    }
}
