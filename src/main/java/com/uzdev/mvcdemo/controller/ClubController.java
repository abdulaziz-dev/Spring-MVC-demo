package com.uzdev.mvcdemo.controller;

import com.uzdev.mvcdemo.dto.ClubDto;
import com.uzdev.mvcdemo.models.Club;
import com.uzdev.mvcdemo.models.UserEntity;
import com.uzdev.mvcdemo.security.SecurityUtility;
import com.uzdev.mvcdemo.service.ClubService;
import com.uzdev.mvcdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    private ClubService clubService;
    private UserService userService;

    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping
    public String listClubs(Model model){
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtility.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/{clubId}")
    public String clubDetails(@PathVariable("clubId") Long clubId, Model model){
        UserEntity user = new UserEntity();
        ClubDto club = clubService.findClubById(clubId);
        String username = SecurityUtility.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", club);
        return "clubs-detail";
    }

    @GetMapping("/search")
    public String searchClubs(@RequestParam(value = "query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto club,
                           BindingResult result){
        if (result.hasErrors()){
            return "clubs-create";
        }
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result){
        if (result.hasErrors()){
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }



}
