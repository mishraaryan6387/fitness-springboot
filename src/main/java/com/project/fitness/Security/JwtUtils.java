package com.project.fitness.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtils {

    private String jwtSecret = "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private int jwtExpirationMs = 172800000;

    public String getJwtFromHeader(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);

        return null;
    }


    // generation of the token

    public String generateToken(String userid, String role) {
        return Jwts.builder()
                .subject(userid)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }


    // validation of token

    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(jwtToken);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // returning the key
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    // getting the user ID from the token


    public String getUserIdFromTheToken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key())
                .build().parseSignedClaims(jwt)
                .getPayload().getSubject();
    }

    // returning the claims of the user

    public Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

}