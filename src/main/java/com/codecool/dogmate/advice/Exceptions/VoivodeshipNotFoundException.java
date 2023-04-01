package com.codecool.dogmate.advice.Exceptions;


public class VoivodeshipNotFoundException extends RuntimeException{
    public VoivodeshipNotFoundException(Integer id) {
            super("Województwo o id " + id + " nie znaleźliśmy");
        }
    }
