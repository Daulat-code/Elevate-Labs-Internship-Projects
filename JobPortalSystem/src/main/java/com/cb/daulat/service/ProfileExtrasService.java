package com.cb.daulat.service;

import java.util.List;

import com.cb.daulat.entity.CertificationEntity;
import com.cb.daulat.entity.ExperienceEntity;
import com.cb.daulat.entity.SkillEntity;

public interface ProfileExtrasService {
	
	public SkillEntity addSkill(String userEmail, String name);
	public CertificationEntity addCertification(String userEmail, String name);
	public ExperienceEntity addExperience(String userEmail, String company, String role, Integer years);
	public List<SkillEntity> listSkills(String userEmail);

}
