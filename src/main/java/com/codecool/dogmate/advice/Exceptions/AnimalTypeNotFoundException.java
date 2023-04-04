package com.codecool.dogmate.advice.Exceptions;


public class AnimalTypeNotFoundException extends RuntimeException{
    public AnimalTypeNotFoundException(Integer id) {
            super("Takiego rodzaju zwierząt  o id " + id + " nie znaleźliśmy");
        }

    public AnimalTypeNotFoundException(String name) {
        super("Takiego rodzaju zwierząt  o nazwie " + name + " nie znaleźliśmy");
    }
}
