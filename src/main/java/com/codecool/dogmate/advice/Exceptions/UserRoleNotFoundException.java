package com.codecool.dogmate.advice.Exceptions;


public class UserRoleNotFoundException extends RuntimeException{
    public UserRoleNotFoundException(Integer id) {
            super("Kategorii użytkownika  o id " + id + " nie znaleźliśmy");
        }
    public UserRoleNotFoundException(String name) {
        super("Kategorii użytkownika  o nazwie " + name + " nie znaleźliśmy");
    }
    }
