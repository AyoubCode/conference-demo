package com.spring.tuto.conferencedemo.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class SessionDTO {
    private Long sessionId;
    private String sessionName;
    private String sessionDescription;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }
}
