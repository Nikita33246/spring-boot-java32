package org.itstep.springbootjava32.repository;


import org.itstep.springbootjava32.model.ERole;
import org.itstep.springbootjava32.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRole(ERole eRole);
}
