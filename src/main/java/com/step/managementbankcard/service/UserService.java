package com.step.managementbankcard.service;

import com.step.managementbankcard.models.User;
import com.step.managementbankcard.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found username" + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,true,true,
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
