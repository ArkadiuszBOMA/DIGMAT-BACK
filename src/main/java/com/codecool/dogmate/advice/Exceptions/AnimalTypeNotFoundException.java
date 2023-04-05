package com.codecool.dogmate.advice.Exceptions;


public class AnimalTypeNotFoundException extends RuntimeException{
    public AnimalTypeNotFoundException(Integer id) {
            super("Rodzaju zwierząt  o id " + id + " nie znaleźliśmy");
        }

    public AnimalTypeNotFoundException(String name) {
        super("Rodzaju zwierząt  o nazwie " + name + " nie znaleźliśmy");
    }
}
