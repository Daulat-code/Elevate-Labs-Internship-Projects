package com.cb.daulat.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cb.daulat.entity.UserEntity;

public interface IUserService {
	public UserEntity registerUser(String email, String password);
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
