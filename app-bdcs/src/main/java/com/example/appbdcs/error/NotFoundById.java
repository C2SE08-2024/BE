package com.example.appbdcs.error;

public class NotFoundById extends Exception {
    public NotFoundById(String error){
        super(error);
    }
}
