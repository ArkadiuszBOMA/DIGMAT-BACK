package com.codecool.dogmate.advice.Exceptions;


public class TimeUnitNotFoundException extends RuntimeException{
    public TimeUnitNotFoundException(Integer id) {
            super("Jednostki miary o id " + id + " nie znaleźliśmy");
        }
    public TimeUnitNotFoundException(String name) {
        super("Jednostki miary o nazwie " + name + " nie znaleźliśmy");
    }
    }
