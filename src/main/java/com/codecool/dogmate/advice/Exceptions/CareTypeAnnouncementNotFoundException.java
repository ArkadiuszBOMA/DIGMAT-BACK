package com.codecool.dogmate.advice.Exceptions;


public class CareTypeAnnouncementNotFoundException extends RuntimeException{
    public CareTypeAnnouncementNotFoundException(Integer id) {
            super("Pomocy o id " + id + " nie znaleźliśmy");
        }
    public CareTypeAnnouncementNotFoundException(String name) {
        super("Pomocy o nazwie " + name + " nie znaleźliśmy");
    }
    }
