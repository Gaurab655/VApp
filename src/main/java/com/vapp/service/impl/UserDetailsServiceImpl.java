package com.vapp.service.impl;

import com.vapp.entity.UserEntity;
import com.vapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(String.valueOf(userEntity.getRole()))
                .build();
    }
}


