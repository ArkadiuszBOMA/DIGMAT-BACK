package com.codecool.dogmate.advice.Exceptions;


public class UserPrivilegesNotFoundException extends RuntimeException{
    public UserPrivilegesNotFoundException(Integer id) {
            super("Roli  o id " + id + " nie znaleźliśmy");
        }
    public UserPrivilegesNotFoundException(String name) {
        super("Roli  o nazwie " + name + " nie znaleźliśmy");
    }
    }
