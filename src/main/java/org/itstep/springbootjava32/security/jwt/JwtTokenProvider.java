package org.itstep.springbootjava32.security.jwt;

import io.jsonwebtoken.*;
import org.itstep.springbootjava32.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        HashMap<String, Object> claimsMap = new HashMap<>();

        claimsMap.put("id",Long.toString(user.getId()));
        claimsMap.put("username", user.getUsername());
        claimsMap.put("email", user.getEmail());
        claimsMap.put("password", user.getPassword());

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .addClaims(claimsMap)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "SecretKeyGenJWT")
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey("SecretKeyGenJWT").parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getUserIdByToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("SecretKeyGenJWT")
                .parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.get("id", String.class));
    }
}
