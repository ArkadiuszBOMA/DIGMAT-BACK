package com.codecool.dogmate.advice.Exceptions;


public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(Integer id) {
            super("Zwierzaka o id " + id + " nie znaleźliśmy");
        }
    public AnimalNotFoundException(String name) {
        super("Zwierzaka o nazwie " + name + " nie znaleźliśmy");
    }
    }
