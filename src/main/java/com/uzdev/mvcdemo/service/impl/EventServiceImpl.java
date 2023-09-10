package com.uzdev.mvcdemo.service.impl;

import com.uzdev.mvcdemo.dto.EventDto;
import com.uzdev.mvcdemo.mappers.EventMapper;
import com.uzdev.mvcdemo.models.Club;
import com.uzdev.mvcdemo.models.Event;
import com.uzdev.mvcdemo.repository.ClubRepository;
import com.uzdev.mvcdemo.repository.EventRepository;
import com.uzdev.mvcdemo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uzdev.mvcdemo.mappers.EventMapper.mapToEvent;
import static com.uzdev.mvcdemo.mappers.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
