package com.codecool.dogmate.controller.advice.Exceptions;


public class ProvincesNotFoundException extends RuntimeException{
    public ProvincesNotFoundException(Integer id) {
            super("Powiatu o id " + id + " nie znaleźliśmy");
        }
    }
