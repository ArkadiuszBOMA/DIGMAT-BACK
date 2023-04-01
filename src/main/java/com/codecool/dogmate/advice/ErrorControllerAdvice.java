package com.codecool.dogmate.advice;

import com.codecool.dogmate.advice.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value ={
            AnimalNotFoundException.class,
            AnimalTypeNotFoundException.class,
            AppUserNotFoundException.class,
            BreadNotFoundException.class,
            CityNotFoundException.class,
            CareTypeAnnouncementNotFoundException.class,
            LessonNotFoundException.class,
            LessonStepNotFoundException.class,
            TimeUnitNotFoundException.class,
            TrainingLevelNotFoundException.class,
            ProvinceNotFoundException.class,
            VoivodeshipNotFoundException.class,
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoItem(RuntimeException exception) {
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
