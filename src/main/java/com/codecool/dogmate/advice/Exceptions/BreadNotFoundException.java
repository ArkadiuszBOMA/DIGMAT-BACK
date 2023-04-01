package com.codecool.dogmate.advice.Exceptions;


public class BreadNotFoundException extends RuntimeException{
    public BreadNotFoundException(Integer id) {
            super("Razy o id " + id + " nie znaleźliśmy");
        }
    }
