package com.spring.tuto.conferencedemo.models;




import com.spring.tuto.conferencedemo.DTOs.responses.SessionDetailsResponse;

import javax.persistence.*;
import java.util.List;

@Entity (name = "sessions")

public class Session {

    private final SessionDetailsResponse sessionDetailsResponse = new SessionDetailsResponse();


    public Session() {
    }

    public Long getSession_id() {
        return sessionDetailsResponse.session_id;
    }

    public void setSession_id(Long session_id) {
        this.sessionDetailsResponse.session_id = session_id;
    }

    public String getSession_name() {
        return sessionDetailsResponse.session_name;
    }

    public void setSession_name(String session_name) {
        this.sessionDetailsResponse.session_name = session_name;
    }

    public String getSession_description() {
        return sessionDetailsResponse.session_description;
    }

    public void setSession_description(String session_description) {
        this.sessionDetailsResponse.session_description = session_description;
    }

    public Integer getSession_length() {
        return sessionDetailsResponse.session_length;
    }

    public void setSession_length(Integer session_length) {
        this.sessionDetailsResponse.session_length = session_length;
    }

    public List<Speaker> getSpeakers() {
        return sessionDetailsResponse.speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.sessionDetailsResponse.speakers = speakers;
    }
}
