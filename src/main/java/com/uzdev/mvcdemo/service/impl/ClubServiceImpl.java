package com.uzdev.mvcdemo.service.impl;

import com.uzdev.mvcdemo.dto.ClubDto;
import com.uzdev.mvcdemo.mappers.ClubMapper;
import com.uzdev.mvcdemo.models.Club;
import com.uzdev.mvcdemo.models.UserEntity;
import com.uzdev.mvcdemo.repository.ClubRepository;
import com.uzdev.mvcdemo.repository.UserRepository;
import com.uzdev.mvcdemo.security.SecurityUtility;
import com.uzdev.mvcdemo.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.uzdev.mvcdemo.mappers.ClubMapper.mapToClub;
import static com.uzdev.mvcdemo.mappers.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public ClubDto saveClub(ClubDto clubDto) {
        String username = SecurityUtility.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return mapToClubDto(clubRepository.save(club));
    }

    @Override
    public ClubDto findClubById(Long id) {
        Club club = clubRepository.findById(id).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtility.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
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
