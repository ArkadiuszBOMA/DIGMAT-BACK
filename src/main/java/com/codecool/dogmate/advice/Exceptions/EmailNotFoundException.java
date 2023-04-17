package com.codecool.dogmate.advice.Exceptions;


public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String name) {
        super("Użytkownika " + name + " nie znaleźliśmy");
    }
    }
