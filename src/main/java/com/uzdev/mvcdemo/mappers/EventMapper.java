package com.uzdev.mvcdemo.mappers;

import com.uzdev.mvcdemo.dto.EventDto;
import com.uzdev.mvcdemo.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .startDate(eventDto.getStartDate())
                .endDate(eventDto.getEndDate())
                .club(eventDto.getClub())
                .build();
    }

    public static EventDto mapToEventDto(Event event){
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
    }
}
