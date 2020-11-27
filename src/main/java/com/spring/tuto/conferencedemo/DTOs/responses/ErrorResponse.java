package com.spring.tuto.conferencedemo.DTOs.responses;

public class ErrorResponse extends AppResponse{

    public ErrorResponse(String message){
        super(false);
        addFullMessage(message);
    }
    public ErrorResponse(){
        this(null);
    }
}
