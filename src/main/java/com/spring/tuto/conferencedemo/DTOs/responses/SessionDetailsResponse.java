package com.spring.tuto.conferencedemo.DTOs.responses;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDetailsResponse extends AppResponse{

    public Long session_id;
    public String session_name;
    public String session_description;


    public SessionDetailsResponse(SessionDTO sessionDTO, String msg) {
        this(sessionDTO.getSessionId(),sessionDTO.getSessionName(),sessionDTO.getSessionDescription());
        addFullMessage(msg);
    }

    public SessionDetailsResponse(SessionDTO sessionDTO){
        this(sessionDTO,null);

    }
}
