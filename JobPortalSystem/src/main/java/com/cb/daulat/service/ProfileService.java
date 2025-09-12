package com.cb.daulat.service;

import com.cb.daulat.entity.ProfileEntity;

public interface ProfileService {
	
	public ProfileEntity getByUserEmail(String email);
	public ProfileEntity saveProfile(ProfileEntity profile, String userEmail);
	public ProfileEntity savePicture(String userEmail, String filename);

}
