package com.uzdev.mvcdemo.controller;

import com.uzdev.mvcdemo.dto.RegisterDto;
import com.uzdev.mvcdemo.models.UserEntity;
import com.uzdev.mvcdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegisterDto user,
                           BindingResult result, Model model){
        UserEntity existingEmailUser = userService.findByEmail(user.getEmail());
        if (existingEmailUser != null && !existingEmailUser.getEmail().isBlank()){
            return "redirect:/register?fail";
        }

        UserEntity existingUsernameUser = userService.findByUsername(user.getUsername());
        if (existingUsernameUser != null && !existingUsernameUser.getEmail().isBlank()){
            return "redirect:/register?fail";        }

        if (result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }



}
