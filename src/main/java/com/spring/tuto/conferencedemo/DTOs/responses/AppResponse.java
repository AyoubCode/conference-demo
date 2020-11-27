package com.spring.tuto.conferencedemo.DTOs.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class AppResponse {
    private Boolean success;
    private List<String> fullMessages;

    public AppResponse(){
        log.info("response created");
    }

    protected AppResponse(boolean success) {
        this.success = success;
        fullMessages = new ArrayList<>();
    }

    public boolean isSuccess() {
        return success;
    }


    protected void addFullMessage(String message) {
        if (message == null)
            return;
        if (fullMessages == null)
            fullMessages = new ArrayList<>();

        fullMessages.add(message);
    }

}
