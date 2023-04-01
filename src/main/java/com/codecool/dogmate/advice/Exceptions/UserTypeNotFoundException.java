package com.codecool.dogmate.advice.Exceptions;


public class UserTypeNotFoundException extends RuntimeException{
    public UserTypeNotFoundException(Integer id) {
            super("Kategorii użytkownika  o id " + id + " nie znaleźliśmy");
        }
    }
