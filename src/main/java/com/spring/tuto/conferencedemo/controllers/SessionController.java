package com.spring.tuto.conferencedemo.controllers;

import com.spring.tuto.conferencedemo.DTOs.SessionDTO;
import com.spring.tuto.conferencedemo.DTOs.responses.*;
import com.spring.tuto.conferencedemo.exceptions.SessionNotFoundException;
import com.spring.tuto.conferencedemo.mapper.SessionMapper;
import com.spring.tuto.conferencedemo.models.Session;
import com.spring.tuto.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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

    /*
        @GetMapping
        public List<SessionDTO> list() {
            List<Session> sessions = this.sessionRepository.findAll();
            return sessions.stream().map(x->mapper.toSessionDTO(x)).collect(Collectors.toList());
        }
    */
    @GetMapping
    public ResponseEntity<AppResponse> list(
            @RequestParam(required = false) String descr,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "sessionId") String sortBy) {

        Pageable pageable = getPageable(page, size, sortBy);
        Page<Session> sessionsList;
        if (descr == null)
            sessionsList = this.sessionRepository.findAll(pageable);
        else {
            sessionsList = this.sessionRepository.findBySessionDescriptionContaining(descr, pageable);
        }

        List<SessionDTO> sessionsDTOs = buildSessionsDTO(sessionsList);
        //or toList simply
        //sessionsList.toList();

        //         constitue object
        //         Response res = new Response(sessionsList.getContent(), sessionsList.getTotalPages(),
        //         sessionsList.getNumber(), sessionsList.getSize());


        return new ResponseEntity<>(new ListSessionResponse(sessionsDTOs), HttpStatus.OK);

    }
    //Demo
    // /api/v1/sessions?page=1&size=5
    // /api/v1/sessions?size=5:                             using default value for page
    // /api/v1/sessions?descr=data&page=1&size=3            pagination & filter by title containing ‘data’

    // /api/v1/sessions/published?page=2                    pagination & filter by ‘published’ status, pas encore implementé::
    //Page<Tutorial> findByPublished(boolean published, Pageable pageable); et passer true lors de l'implémentation

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<AppResponse> get(@PathVariable Long id) {
        Optional<SessionDTO> resp = Optional.ofNullable(mapper.toSessionDTO(sessionRepository.getOne(id)));
        if (resp.isPresent()) {
            return new ResponseEntity<>(new SessionDetailsResponse(resp.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Bad request"), HttpStatus.NOT_FOUND);

    }

    /*
        @PostMapping
        public ResponseEntity<SessionDTO> create(@RequestBody final SessionDTO sessionDto){
            Session sessionCreated = sessionRepository.saveAndFlush(mapper.toSession(sessionDto));
            return new ResponseEntity<>(mapper.toSessionDTO(sessionCreated), HttpStatus.CREATED);

        }
    */
    @PostMapping
    public ResponseEntity<AppResponse> create(@Valid @RequestBody final Session session) {
        Session sessionCreated = sessionRepository.saveAndFlush(session);
        return new ResponseEntity<>(new SessionDetailsResponse(mapper.toSessionDTO(sessionCreated)), HttpStatus.CREATED);
    }

    //@PutMapping("{id}")
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<AppResponse> update(@RequestBody SessionDTO sessionDto, @PathVariable Long id) {
        Optional<Session> optionalSession = Optional.of(sessionRepository.getOne(id));
        //another way to show how to deal with exception, we could return new ErrorResponse and httpstatus as NotFound
        if (!optionalSession.isPresent()) throw new SessionNotFoundException();

        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(mapper.toSession(sessionDto), existingSession, "session_id");
        Session sessionUpdated = sessionRepository.saveAndFlush(existingSession);
        SessionDTO sessionDTO = mapper.toSessionDTO(sessionUpdated);
        return ResponseEntity.ok(new SessionDetailsResponse((sessionDTO), "session updated succefully"));
    }

    @DeleteMapping("{id}")
    //@RequestMapping(method = RequestMethod.DELETE, value ="{id}")
    public ResponseEntity<AppResponse> delete(@PathVariable Long id) {
        Optional<Session> optionalSession = Optional.ofNullable(sessionRepository.getOne(id));
        if (optionalSession.isPresent()) {
            sessionRepository.deleteById(id);
            return ResponseEntity.ok(new SuccessResponse("Session Deleted Succefully"));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Session Not Found"), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    public AppResponse deleteAll() {
        sessionRepository.deleteAll();
        return new SuccessResponse("Delete All Sessions Succefully");
    }


    //in service layer
    private Pageable getPageable(int page, int size, String sortBy) {
        if (page <= 0)
            page = 1;

        if (size <= 0)
            size = 5;

        //PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "session_id");
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortBy).descending());
        return pageRequest;
    }

    private List<SessionDTO> buildSessionsDTO(Page<Session> sessions) {
        return sessions.getContent().stream().map(x -> mapper.toSessionDTO(x)).collect(Collectors.toList());

    }
}
