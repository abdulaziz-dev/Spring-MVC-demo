package com.uzdev.mvcdemo.controller;

import com.uzdev.mvcdemo.dto.EventDto;
import com.uzdev.mvcdemo.models.Event;
import com.uzdev.mvcdemo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String eventList(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto event = eventService.findByEventId(eventId);
        model.addAttribute("event", event);
        return "events-detail";
    }

    @GetMapping("{eventId}/edit")
    public String editEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto event = eventService.findByEventId(eventId);
        model.addAttribute("event", event);
        return "events-edit";
    }

    @PostMapping("{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @ModelAttribute("event") EventDto event){
        event.setId(eventId);
        EventDto eventDto = eventService.findByEventId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("event", event);
        model.addAttribute("clubId", clubId);
        return "events-create";
    }

    @PostMapping("/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto){
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

}
