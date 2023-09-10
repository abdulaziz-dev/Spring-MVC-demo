package com.uzdev.mvcdemo.service.impl;

import com.uzdev.mvcdemo.dto.ClubDto;
import com.uzdev.mvcdemo.mappers.ClubMapper;
import com.uzdev.mvcdemo.models.Club;
import com.uzdev.mvcdemo.repository.ClubRepository;
import com.uzdev.mvcdemo.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.uzdev.mvcdemo.mappers.ClubMapper.mapToClub;
import static com.uzdev.mvcdemo.mappers.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public ClubDto saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        return mapToClubDto(clubRepository.save(club));
    }

    @Override
    public ClubDto findClubById(Long id) {
        Club club = clubRepository.findById(id).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

}
