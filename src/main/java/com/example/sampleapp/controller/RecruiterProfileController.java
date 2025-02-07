package com.example.sampleapp.controller;

import com.example.sampleapp.entity.RecruiterProfile;
import com.example.sampleapp.entity.Users;
import com.example.sampleapp.repository.UsersRepository;
import com.example.sampleapp.services.RecruiterProfileService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;

    public RecruiterProfileController(UsersRepository usersRepository, RecruiterProfileService recruiterProfileService) {
        this.usersRepository = usersRepository;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUserName = authentication.getName();
            Users user = usersRepository.findByEmail(currentUserName).orElseThrow(()-> new UsernameNotFoundException("Could not find user"));
            Optional<RecruiterProfile> recruiterProfile= recruiterProfileService.getRecruiterProfileById(user.getUserId());

            if(!recruiterProfile.isEmpty()){
                model.addAttribute("profile", recruiterProfile.get());
            }
        }

        return "recruiter_profile";
    }
}
