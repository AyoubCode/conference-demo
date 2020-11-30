package com.spring.tuto.conferencedemo.DTOs.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import lombok.Data;
import lombok.Getter;

import java.util.Collection;

@Data
public class ListSessionResponse extends AppResponse{
    private  final  Collection<SessionDTO> sessionDTOS;


    public ListSessionResponse(Collection<SessionDTO> sessionDTOS) {
        this.sessionDTOS = sessionDTOS;
    }

    public Collection<SessionDTO> getSessionDTOS() {
        return sessionDTOS;
    }


}
