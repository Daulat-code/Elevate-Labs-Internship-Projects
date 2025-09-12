package com.cb.daulat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cb.daulat.entity.ProfileEntity;
import com.cb.daulat.service.ProfileExtrasService;
import com.cb.daulat.service.ProfileService;
import com.cb.daulat.service.StorageService;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequestMapping("/profile")
public class MyProfileController {

    private final ProfileService profileService;
    private final StorageService storage;
    private final ProfileExtrasService extras;

    public MyProfileController(ProfileService profileService, StorageService storage, ProfileExtrasService extras) {
        this.profileService = profileService; this.storage = storage; this.extras = extras;
    }

    @GetMapping("/profile_form")
    public String createProfileForm(Model model) {
        model.addAttribute("profile", new ProfileEntity());
        return "profile_form"; // renders profile_form.html
    }
    @GetMapping("/viewProfile")
    public String viewProfile(Model model, Principal principal) {
        String email = principal.getName();
        ProfileEntity p = profileService.getByUserEmail(email);
        model.addAttribute("profile", p);
        model.addAttribute("skills", extras.listSkills(email));
        // add certifications & experiences similarly
        return "jobseekerProfile";
    }

    @PostMapping("/save")
    public String saveProfile(@ModelAttribute ProfileEntity profile, Principal principal) {
        profileService.saveProfile(profile, principal.getName());
        return "viewProfile";
    }

    @PostMapping("/picture")
    public String uploadPicture(@RequestParam("picture") MultipartFile picture, Principal principal) throws IOException {
        String filename = storage.store(picture);
        profileService.savePicture(principal.getName(), filename);
        return "redirect:/profile";
    }
}
