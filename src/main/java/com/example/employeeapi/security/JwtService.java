package com.example.employeeapi.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm; // You will also need this to sign the token
import java.util.Date;                      // Needed for setting expiration dates


@Service
public class JwtService {


    private final String secretKey;
    private final String issuer;
    private final long expirationTime;

    public JwtService(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.expiration}") long expirationTime) {

        this.secretKey = secretKey;
        this.issuer = issuer;
        this.expirationTime = expirationTime;
    }

    public String generateToken(String username)
    {
        return JWT.create()
                .withSubject(username)
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secretKey));
    }

    /**
     * Extracts the subject (username) from a valid JWT token.
     */

    public String extractUsername(String token)
    {
        return decodeToken(token)
                .getSubject();    }


    /**
     * Validates whether a token is structurally valid and not expired.
     */
    public boolean isTokenValid(String token)
    {
            try{
                DecodedJWT decodedJWT = decodeToken(token);
                return !decodedJWT.getExpiresAt().before(new Date());
            }catch (Exception e)
            {
                return false;
            }
    }

    private DecodedJWT decodeToken(String token)
    {
        return JWT.require(Algorithm.HMAC512(secretKey))
                .withIssuer(issuer)
                .build()
                .verify(token);
    }

}
