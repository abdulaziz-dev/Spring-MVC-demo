package com.uzdev.mvcdemo.repository;

import com.uzdev.mvcdemo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
