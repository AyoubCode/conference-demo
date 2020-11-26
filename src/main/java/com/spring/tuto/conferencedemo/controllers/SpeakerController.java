package com.spring.tuto.conferencedemo.controllers;

import com.spring.tuto.conferencedemo.models.Session;
import com.spring.tuto.conferencedemo.models.Speaker;
import com.spring.tuto.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getOne(id);
    }
     @PostMapping
     public void create(@RequestBody final Speaker speaker){
        speakerRepository.saveAndFlush(speaker);
        }

        @RequestMapping(method = RequestMethod.PUT,value = "{id}")
        public Speaker update(@RequestBody  Speaker speaker, @PathVariable Long id){
        Speaker existingSpeaker = speakerRepository.getOne(id);

            BeanUtils.copyProperties(speaker, existingSpeaker,"session_id");
            return speakerRepository.saveAndFlush(existingSpeaker);
        }

        @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
        public void delete(@PathVariable Long id){
          //attention delete cascade , you have to delete childrens
        speakerRepository.deleteById(id);
        }
 }


 

