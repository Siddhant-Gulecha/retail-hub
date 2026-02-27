package com.nexus.retail_engine.security;


import com.nexus.retail_engine.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {


    @Value("${jwt.SecretKey}")
    private String jwtSecretKey;

    // casts string key to SecretKey object
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts
                .builder()
                .subject(user.getUsername()) // in jwt payload {"sub": "username here"}
                .claim("userId", user.getId().toString()) // claim in jwt payload {"claim": "1234"}
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*20)) // 20 mins till expiration of token
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                        .verifyWith(getSecretKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        return claims.getSubject();
    }


}
