package com.cb.daulat.service.serviceImpl;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cb.daulat.entity.UserEntity;
import com.cb.daulat.repository.UserRepo;
import com.cb.daulat.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Spring injects UserRepo + PasswordEncoder beans automatically
    public UserServiceImpl(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new user with encoded password
     */
    @Override
    public UserEntity registerUser(String email, String password) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // ✅ BCrypt encoded
        user.setRole("ROLE_USER"); // default role (important for Spring Security)
        return userRepository.save(user);
    }

    /**
     * Load user for authentication (Spring Security internal call)
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
