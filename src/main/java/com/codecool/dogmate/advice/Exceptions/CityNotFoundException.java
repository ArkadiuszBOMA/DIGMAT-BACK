package com.codecool.dogmate.advice.Exceptions;


public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(Integer id) {
            super("Miasta o id " + id + " nie znaleźliśmy");
        }
    public CityNotFoundException(String name) {
        super("Miasta o nazwie " + name + " nie znaleźliśmy");
    }
    }
