package com.newts.newtapp.api.security;

import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class NewtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public NewtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = null;
        try {
            user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
        } catch (UserNotFound ignored) {
        }

        return new NewtUserPrincipal(user);
    }
}
