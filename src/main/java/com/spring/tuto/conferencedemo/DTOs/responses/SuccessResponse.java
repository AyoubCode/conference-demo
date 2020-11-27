package com.spring.tuto.conferencedemo.DTOs.responses;


    public class SuccessResponse extends AppResponse {


        public SuccessResponse(String message) {
            super(true);
            addFullMessage(message);
        }

        public SuccessResponse() {
            this(null);
        }
    }

