package com.uzdev.mvcdemo.mappers;

import com.uzdev.mvcdemo.dto.ClubDto;
import com.uzdev.mvcdemo.models.Club;

import java.util.stream.Collectors;

import static com.uzdev.mvcdemo.mappers.EventMapper.mapToEvent;
import static com.uzdev.mvcdemo.mappers.EventMapper.mapToEventDto;

public class ClubMapper {
    public static ClubDto mapToClubDto(Club club) {
        ClubDto dto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .photoUrl(club.getPhotoUrl())
                .createdBy(club.getCreatedBy())
                .build();
        if (club.getEvents() != null){
            dto.setEvents(club.getEvents().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList()));
        }
        return dto;
    }

    public static Club mapToClub(ClubDto clubDto){
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .photoUrl(clubDto.getPhotoUrl())
                .createdBy(clubDto.getCreatedBy())
                .build();
        if (club.getEvents() != null){
            club.setEvents(clubDto.getEvents().stream().map(event -> mapToEvent(event)).collect(Collectors.toList()));
        }
        return club;
    }
}
