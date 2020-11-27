package com.spring.tuto.conferencedemo.repositories;

import com.spring.tuto.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
