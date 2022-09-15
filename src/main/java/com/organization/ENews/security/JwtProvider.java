package com.organization.ENews.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JwtProvider.class);

    String secretKey = "suman";
    long expirationTime = 86400;

    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.UserDetails user = (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
        String jwtToken = Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTime * 1000))
                .signWith(SignatureAlgorithm.RS256, secretKey)
                .compact();
        return jwtToken;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            LOGGER.error("JWT PARSING ERROR - > " + e);
            return false;
        }

    }

    public String getUsernameFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody().getSubject();
    }


}
