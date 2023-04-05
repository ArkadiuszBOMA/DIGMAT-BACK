package com.codecool.dogmate.advice.Exceptions;


public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException(Integer id) {
            super("Użytkownika  o id " + id + " nie znaleźliśmy");
        }
    public AppUserNotFoundException(String name) {
        super("Użytkownika  o nazwie " + name + " nie znaleźliśmy");
    }
    }
