package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.ERole;
import org.itstep.springbootjava32.model.Role;
import org.itstep.springbootjava32.model.User;
import org.itstep.springbootjava32.repository.RoleRepository;
import org.itstep.springbootjava32.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private  UserRepository userRepository;

    private  RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


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


    public void saveUser(User user) {
        if (user != null) {
            User savedUser = user;
            savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findRoleByRole(ERole.ROLE_STUDENT).get();
            savedUser.setRoles(new HashSet<>(List.of(role)));
            userRepository.save(savedUser);
        }
    }
}
