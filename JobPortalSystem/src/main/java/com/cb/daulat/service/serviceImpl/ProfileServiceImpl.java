package com.cb.daulat.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.cb.daulat.entity.ProfileEntity;
import com.cb.daulat.entity.UserEntity;
import com.cb.daulat.repository.ProfileRepository;
import com.cb.daulat.repository.UserRepo;
import com.cb.daulat.service.ProfileService;
import com.cb.daulat.service.StorageService;


@Service
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepo;
    private final UserRepo userRepo;
    private final StorageService storage;

    public ProfileServiceImpl(ProfileRepository profileRepo, UserRepo userRepo, StorageService storage) {
        this.profileRepo = profileRepo; this.userRepo = userRepo; this.storage = storage;
    }

    @Override
    public ProfileEntity getByUserEmail(String email) {
        UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return profileRepo.findByUser(user).orElse(new ProfileEntity());
    }

    @Override
    public ProfileEntity saveProfile(ProfileEntity profile, String userEmail) {
        UserEntity user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        return profileRepo.save(profile);
    }

    @Override
    public ProfileEntity savePicture(String userEmail, String filename) {
        UserEntity user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        ProfileEntity p = profileRepo.findByUser(user).orElse(new ProfileEntity());
        p.setUser(user);
        p.setPicturePath(filename);
        return profileRepo.save(p);
    }
}