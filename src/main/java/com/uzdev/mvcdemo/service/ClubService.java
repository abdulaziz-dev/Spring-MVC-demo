package com.uzdev.mvcdemo.service;

import com.uzdev.mvcdemo.dto.ClubDto;
import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    ClubDto saveClub(ClubDto club);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto club);

    void delete(Long clubId);

    List<ClubDto> searchClubs(String query);
}
