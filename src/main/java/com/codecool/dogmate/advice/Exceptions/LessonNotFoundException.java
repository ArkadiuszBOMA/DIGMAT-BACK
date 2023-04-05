package com.codecool.dogmate.advice.Exceptions;


public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(Integer id) {
            super("Lekcji o id " + id + " nie znaleźliśmy");
        }
    public LessonNotFoundException(String name) {
        super("Lekcji o nazwie " + name + " nie znaleźliśmy");
    }
    }
