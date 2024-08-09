package org.itstep.springbootjava32.repository;


import org.itstep.springbootjava32.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken getVerificationTokenByToken(String token);

}
