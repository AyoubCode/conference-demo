package com.spring.tuto.conferencedemo.mapper;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import com.spring.tuto.conferencedemo.models.Session;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionMapper  MAPPER=Mappers.getMapper(SessionMapper.class);

    //dont need that when we have the same properties name's, in the be
    // gining it wansnt the case so i was forced to do that
    /*
    @Mappings({

            @Mapping(target="sessionId",source = "session.sessionId"),
            @Mapping(target="sessionName",source = "session.sessionName"),
            @Mapping(target="sessionDescription",source = "session.sessionDescription")
    })
    */

    SessionDTO toSessionDTO(Session session);


    List<SessionDTO> toSessionDTOs(List<Session> sessions);


   @InheritInverseConfiguration(name="toSessionDTO")
    Session toSession(SessionDTO sessionDTO);

}
