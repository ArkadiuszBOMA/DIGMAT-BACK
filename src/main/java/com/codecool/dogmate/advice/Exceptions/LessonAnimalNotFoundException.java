package com.codecool.dogmate.advice.Exceptions;


public class LessonAnimalNotFoundException extends RuntimeException{
    public LessonAnimalNotFoundException(Integer id) {
            super("Lekcji dla twojego zwierzaka o id " + id + " nie znaleźliśmy");
        }
    public LessonAnimalNotFoundException(String name) {
        super("Lekcji dla twojego zwierzaka o nazwie " + name + " nie znaleźliśmy");
    }
    }
