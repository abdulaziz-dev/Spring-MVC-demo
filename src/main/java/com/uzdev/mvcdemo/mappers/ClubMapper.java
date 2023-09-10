package com.uzdev.mvcdemo.mappers;

import com.uzdev.mvcdemo.dto.ClubDto;
import com.uzdev.mvcdemo.models.Club;

import java.util.stream.Collectors;

import static com.uzdev.mvcdemo.mappers.EventMapper.mapToEvent;
import static com.uzdev.mvcdemo.mappers.EventMapper.mapToEventDto;

public class ClubMapper {
    public static ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .photoUrl(club.getPhotoUrl())
                .events(club.getEvents().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
    }

    public static Club mapToClub(ClubDto clubDto){
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .photoUrl(clubDto.getPhotoUrl())
//                .events(clubDto.getEvents().stream().map((eventDto -> mapToEvent(eventDto))).collect(Collectors.toList()))
                .build();
    }
}
