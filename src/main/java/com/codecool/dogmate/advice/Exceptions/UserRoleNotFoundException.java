package com.codecool.dogmate.advice.Exceptions;


public class UserRoleNotFoundException extends RuntimeException{
    public UserRoleNotFoundException(Integer id) {
            super("Roli  o id " + id + " nie znaleźliśmy");
        }
    public UserRoleNotFoundException(String name) {
        super("Roli  o nazwie " + name + " nie znaleźliśmy");
    }
    }
