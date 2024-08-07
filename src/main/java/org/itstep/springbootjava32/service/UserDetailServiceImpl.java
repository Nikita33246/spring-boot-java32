package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.User;
import org.itstep.springbootjava32.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("UserDetail username = " + username);

        Optional<User> byUsername = userRepository.findByUsername(username);
        Optional<User> byEmail = userRepository.findByEmail(username);

        if (byUsername.isPresent()) {
            System.out.println("byUsername = isPresent");
            return byUsername.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        } else if (byEmail.isPresent()) {
            System.out.println("byEmail = isPresent");
            return byEmail.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        }
        return null;
    }
}
