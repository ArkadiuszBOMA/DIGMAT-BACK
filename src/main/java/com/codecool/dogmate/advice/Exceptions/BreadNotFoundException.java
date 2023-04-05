package com.codecool.dogmate.advice.Exceptions;


public class BreadNotFoundException extends RuntimeException{
    public BreadNotFoundException(Integer id) {
            super("Rasy o id " + id + " nie znaleźliśmy");
        }
    public BreadNotFoundException(String name) {
        super("Rasy o nazwie " + name + " nie znaleźliśmy");
    }
    }
