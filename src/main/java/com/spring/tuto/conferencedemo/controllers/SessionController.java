package com.spring.tuto.conferencedemo.controllers;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import com.spring.tuto.conferencedemo.mapper.SessionMapper;
import com.spring.tuto.conferencedemo.models.Session;
import com.spring.tuto.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionMapper mapper;

    @GetMapping
    public List<SessionDTO> list() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(x->mapper.toSessionDTO(x)).collect(Collectors.toList());
    }

     @GetMapping
     @RequestMapping("{id}")
    public SessionDTO get(@PathVariable Long id){
        return mapper.toSessionDTO(sessionRepository.getOne(id));
 }

    @PostMapping
    public SessionDTO create(@RequestBody final SessionDTO sessionDto){
        Session sessionCreated = sessionRepository.saveAndFlush(mapper.toSession(sessionDto));
        return (mapper.toSessionDTO(sessionCreated));

    }

    @RequestMapping(method= RequestMethod.PUT, value="{id}")
    public SessionDTO update(@RequestBody SessionDTO sessionDto , @PathVariable Long id){
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(mapper.toSession(sessionDto),existingSession, "session_id");
       Session sessionUpdated = sessionRepository.saveAndFlush(existingSession);
       return (mapper.toSessionDTO(sessionUpdated));
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="{id}")
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }
}
