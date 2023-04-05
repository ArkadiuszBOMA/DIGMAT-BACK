package com.codecool.dogmate.advice.Exceptions;


public class LessonStepNotFoundException extends RuntimeException{
    public LessonStepNotFoundException(Integer id) {
            super("Kroku o id " + id + " nie znaleźliśmy");
        }
    public LessonStepNotFoundException(String name) {
        super("Kroku o nazwie " + name + " nie znaleźliśmy");
    }
    }
