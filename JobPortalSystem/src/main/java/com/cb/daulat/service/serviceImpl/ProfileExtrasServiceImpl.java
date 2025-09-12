package com.cb.daulat.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.cb.daulat.entity.CertificationEntity;
import com.cb.daulat.entity.ExperienceEntity;
import com.cb.daulat.entity.SkillEntity;
import com.cb.daulat.entity.UserEntity;
import com.cb.daulat.repository.CertificationRepository;
import com.cb.daulat.repository.ExperienceRepository;
import com.cb.daulat.repository.SkillRepository;
import com.cb.daulat.repository.UserRepo;
import com.cb.daulat.service.ProfileExtrasService;

import java.util.List;

@Service
public class ProfileExtrasServiceImpl implements ProfileExtrasService {
    private final SkillRepository skillRepo;
    private final CertificationRepository certRepo;
    private final ExperienceRepository expRepo;
    private final UserRepo userRepo;

    public ProfileExtrasServiceImpl(SkillRepository skillRepo, CertificationRepository certRepo,
                                ExperienceRepository expRepo, UserRepo userRepo) {
        this.skillRepo = skillRepo; this.certRepo = certRepo; this.expRepo = expRepo; this.userRepo = userRepo;
    }

    @Override
    public SkillEntity addSkill(String userEmail, String name) {
        UserEntity user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        SkillEntity s = new SkillEntity(); s.setName(name); s.setUser(user); return skillRepo.save(s);
    }
    
    @Override
    public CertificationEntity addCertification(String userEmail, String name) {
        UserEntity user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        CertificationEntity c = new CertificationEntity(); c.setName(name); c.setUser(user); return certRepo.save(c);
    }
    
    @Override
    public ExperienceEntity addExperience(String userEmail, String company, String role, Integer years) {
        UserEntity user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        ExperienceEntity e = new ExperienceEntity(); e.setCompany(company); e.setRole(role); e.setYears(years); e.setUser(user); return expRepo.save(e);
    }
    
    @Override
    public List<SkillEntity> listSkills(String userEmail) {
        UserEntity user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        return skillRepo.findByUser(user);
    }
    // similar list methods for certs and experiences
}
