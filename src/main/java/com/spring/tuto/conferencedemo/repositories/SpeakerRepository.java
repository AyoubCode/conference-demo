package com.spring.tuto.conferencedemo.repositories;

import com.spring.tuto.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
