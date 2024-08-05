package org.itstep.springbootjava32.security;


import org.itstep.springbootjava32.model.User;
import org.itstep.springbootjava32.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyAuthProvider implements AuthenticationProvider {

    private  UserRepository userRepository;

    private  BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<User> byUsername = userRepository.findByUsername(authentication.getName());
        //Optional<User> byEmail = userRepository.findByEmail(authentication.getName());

        User user;
        if (byUsername.isPresent()) {
            user = byUsername.get();
        } else {
            throw new UsernameNotFoundException("Username not found");
        }

        String password = authentication.getCredentials().toString();
//        if(!password.equals(user.getPassword())) {
//            throw new BadCredentialsException("Bad credentials");
//        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            System.out.println("password = " + password +  " Bad credentials");
            throw new BadCredentialsException("Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
