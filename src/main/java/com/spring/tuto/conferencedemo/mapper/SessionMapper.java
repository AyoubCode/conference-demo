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
    @Mappings({
            @Mapping(target="sessionId",source = "session.session_id"),
            @Mapping(target="sessionName",source = "session.session_name"),
            @Mapping(target="sessionDescription",source = "session.session_description")
    })
    SessionDTO toSessionDTO(Session session);


    List<SessionDTO> toSessionDTOs(List<Session> sessions);


   @InheritInverseConfiguration(name="toSessionDTO")
    Session toSession(SessionDTO sessionDTO);

}
