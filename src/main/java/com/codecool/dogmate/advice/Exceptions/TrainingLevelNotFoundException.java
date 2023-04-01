package com.codecool.dogmate.advice.Exceptions;


public class TrainingLevelNotFoundException extends RuntimeException{
    public TrainingLevelNotFoundException(Integer id) {
            super("Poziomu trudności lekcji o id " + id + " nie znaleźliśmy");
        }
    }
