package com.spring.tuto.conferencedemo.controllers;

import com.spring.tuto.conferencedemo.models.Session;
import com.spring.tuto.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

     @GetMapping
     @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
 }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);

    }

    @RequestMapping(method= RequestMethod.PUT, value="{id}")
    public Session update(@RequestBody Session session , @PathVariable Long id){
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="{id}")
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }
}
