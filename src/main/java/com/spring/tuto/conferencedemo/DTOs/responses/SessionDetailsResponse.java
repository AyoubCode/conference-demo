package com.spring.tuto.conferencedemo.DTOs.responses;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import com.spring.tuto.conferencedemo.models.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class SessionDetailsResponse extends AppResponse{

    public Long sessionId;
    public String sessionName;
    public String sessionDescription;

    public SessionDetailsResponse(Long sessionId, String sessionName, String sessionDescription) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionDescription = sessionDescription;
    }

    public SessionDetailsResponse(SessionDTO sessionDTO, String msg) {
        this(sessionDTO.getSessionId(),sessionDTO.getSessionName(),sessionDTO.getSessionDescription());
        addFullMessage(msg);
    }

    public SessionDetailsResponse(SessionDTO sessionDTO){
        this(sessionDTO,null);

    }
    public static SessionDetailsResponse build(Session session) {
       return new SessionDetailsResponse(session.getSessionId(),session.getSessionName(), session.getSessionDescription());

    }
}
