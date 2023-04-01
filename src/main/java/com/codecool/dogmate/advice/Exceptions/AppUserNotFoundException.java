package com.codecool.dogmate.advice.Exceptions;


public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException(Integer id) {
            super("Użytkownika  o id " + id + " nie znaleźliśmy");
        }
    }
