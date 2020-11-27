package com.spring.tuto.conferencedemo.controllers;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import com.spring.tuto.conferencedemo.DTOs.responses.AppResponse;
import com.spring.tuto.conferencedemo.DTOs.responses.ErrorResponse;
import com.spring.tuto.conferencedemo.DTOs.responses.SessionDetailsResponse;
import com.spring.tuto.conferencedemo.DTOs.responses.SuccessResponse;
import com.spring.tuto.conferencedemo.exceptions.SessionNotFoundException;
import com.spring.tuto.conferencedemo.mapper.SessionMapper;
import com.spring.tuto.conferencedemo.models.Session;
import com.spring.tuto.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;
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
        List<Session> sessions = this.sessionRepository.findAll();
        return sessions.stream().map(x->mapper.toSessionDTO(x)).collect(Collectors.toList());
    }

     @GetMapping
     @RequestMapping("{id}")
    public ResponseEntity<AppResponse> get(@PathVariable Long id){
        Optional<SessionDTO> resp = Optional.ofNullable(mapper.toSessionDTO(sessionRepository.getOne(id)));
        if(resp.isPresent()){
            return new ResponseEntity<>(new SessionDetailsResponse(resp.get()),HttpStatus.OK);
        }
            return new ResponseEntity<>( new ErrorResponse("Bad request"),HttpStatus.NOT_FOUND);

 }

    @PostMapping
    public SessionDTO create(@RequestBody final SessionDTO sessionDto){
        Session sessionCreated = sessionRepository.saveAndFlush(mapper.toSession(sessionDto));
        return (mapper.toSessionDTO(sessionCreated));

    }

    //@PutMapping("{id}")
    @RequestMapping(method= RequestMethod.PUT, value="{id}")
    public ResponseEntity<AppResponse>  update(@RequestBody SessionDTO sessionDto , @PathVariable Long id){
           Optional<Session> optionalSession = Optional.of(sessionRepository.getOne(id));
      //another way to show how to deal with exception, we could return new ErrorResponse and httpstatus as NotFound
        if(!optionalSession.isPresent())throw new SessionNotFoundException();

        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(mapper.toSession(sessionDto),existingSession, "session_id");
       Session sessionUpdated = sessionRepository.saveAndFlush(existingSession);
        SessionDTO sessionDTO =mapper.toSessionDTO(sessionUpdated);
       return ResponseEntity.ok(new SessionDetailsResponse((sessionDTO),"session updated succefully"));
    }

    @DeleteMapping("{id}")
    //@RequestMapping(method = RequestMethod.DELETE, value ="{id}")
    public ResponseEntity<AppResponse> delete(@PathVariable Long id){
        Optional<Session> optionalSession = Optional.ofNullable(sessionRepository.getOne(id));
        if(optionalSession.isPresent()) {
            sessionRepository.deleteById(id);
            return ResponseEntity.ok(new SuccessResponse("Session Deleted Succefully"));
        }else {
            return new ResponseEntity<>(new ErrorResponse("Session Not Found"), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    public AppResponse deleteAll(){
        sessionRepository.deleteAll();
        return new SuccessResponse("Delete All Sessions Succefully");
    }
}
