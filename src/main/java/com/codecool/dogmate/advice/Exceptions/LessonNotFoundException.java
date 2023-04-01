package com.codecool.dogmate.advice.Exceptions;


public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(Integer id) {
            super("Lekcji o id " + id + " nie znaleźliśmy");
        }
    }
