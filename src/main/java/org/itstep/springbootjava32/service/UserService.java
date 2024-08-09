package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.ERole;
import org.itstep.springbootjava32.model.Role;
import org.itstep.springbootjava32.model.User;
import org.itstep.springbootjava32.model.VerificationToken;
import org.itstep.springbootjava32.repository.RoleRepository;
import org.itstep.springbootjava32.repository.UserRepository;
import org.itstep.springbootjava32.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

@Service
public class UserService {

    private  UserRepository userRepository;

    private  RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private VerificationTokenRepository verificationTokenRepository;


    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    public void saveStudentUser(User user) {
        if (user != null) {
            User savedUser = user;
            savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findRoleByRole(ERole.ROLE_STUDENT).get();
            savedUser.setRoles(new HashSet<>(List.of(role)));
            userRepository.save(savedUser);
        }
    }

    public void saveAdminUser(User user) {
        if (user != null) {
            User savedUser = user;
            savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findRoleByRole(ERole.ROLE_ADMIN).get();
            savedUser.setRoles(new HashSet<>(List.of(role)));
            userRepository.save(savedUser);
        }
    }

    public void createToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user.getId(), token, LocalDateTime.now());
        verificationTokenRepository.save(verificationToken);

    }


    public VerificationToken getVerificationTokenByToken(String token) {
       return verificationTokenRepository.getVerificationTokenByToken(token);
    }

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
