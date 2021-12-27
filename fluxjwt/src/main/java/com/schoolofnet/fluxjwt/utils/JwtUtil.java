package com.schoolofnet.fluxjwt.utils;

import com.schoolofnet.fluxjwt.model.document.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.function.Function;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Component
public class JwtUtil implements Serializable {

    private static final String MY_SECRET = "MY_SECRET";

    public String getUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(MY_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, MY_SECRET)
                .compact();
    }
}
