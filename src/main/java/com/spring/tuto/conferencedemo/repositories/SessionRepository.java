package com.spring.tuto.conferencedemo.repositories;

import com.spring.tuto.conferencedemo.models.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {



    Page<Session> findAll(Pageable pageRequest);
    //forced to change session_description to sessionDescription because _ is reserved in spring data
    Page<Session> findBySessionDescriptionContaining(String descr, Pageable pageable);
}
