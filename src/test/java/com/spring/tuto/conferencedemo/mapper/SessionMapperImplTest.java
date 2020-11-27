package com.spring.tuto.conferencedemo.mapper;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import com.spring.tuto.conferencedemo.models.Session;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionMapperImplTest {
    //can use that as simple test without spring ioc
    //private SessionMapper mapper= Mappers.getMapper(SessionMapper.class);

    @Autowired
    SessionMapper mapper;

    @Test
    void toSessionDTO_whenMaps_thenCorrect() {
        Session session = new Session();
        session.setSession_id(1L);
        session.setSession_name("first_session");
        session.setSession_description("sessionDescription");
        SessionDTO sessionDTO=mapper.toSessionDTO(session);

        assertEquals(session.getSession_id(),sessionDTO.getSessionId());
        assertEquals(session.getSession_description(),sessionDTO.getSessionDescription());

    }

    @Test
    void toListSessionDTOs_whenMaps_thenCorrect() {
        Session session = new Session();
        session.setSession_id(1L);
        session.setSession_name("first_session");
        session.setSession_description("sessionDescription");

        Session session2 = new Session();
        session2.setSession_id(1L);
        session2.setSession_name("first_session");
        session2.setSession_description("sessionDescription");

        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session);
        sessionList.add(session2);

        List<SessionDTO> sessionsDtoList = mapper.toSessionDTOs(sessionList);


        assertEquals(session.getSession_id(),sessionsDtoList.get(0).getSessionId());
        assertEquals(session2.getSession_id(),sessionsDtoList.get(1).getSessionId());





    }

    @Test
    void toSession_whenMaps_thenCorrect() {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSessionId(2L);
        sessionDTO.setSessionDescription("dtoDesc");
        sessionDTO.setSessionName("dtoName");

        Session session = mapper.toSession(sessionDTO);
        assertEquals(sessionDTO.getSessionId(), session.getSession_id());
        assertEquals(sessionDTO.getSessionDescription(), session.getSession_description());

    }
}
