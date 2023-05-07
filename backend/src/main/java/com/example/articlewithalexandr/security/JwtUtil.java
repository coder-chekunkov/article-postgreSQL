package com.example.articlewithalexandr.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.articlewithalexandr.dto.JwtTokenResponse;
import com.example.articlewithalexandr.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Vlad Utts
 */
@Component
public class JwtUtil {
    @Value("${jwt.secret.access}")
    private String accessSecret;
    @Value("${jwt.secret.refresh}")
    private String refreshSecret;
    private static final String USER_DETAILS = "User details";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String ISSUER = "ConcreteIssuer";

    private String generateAccessToken(User user) {
        Date issuedDate = new Date();
        Date expirationDate = Date.from(ZonedDateTime.now().plusHours(1).toInstant());

        return JWT.create()
                .withSubject(USER_DETAILS)
                .withClaim(ID, user.getId())
                .withClaim(EMAIL, user.getEmail())
                .withIssuedAt(issuedDate)
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(accessSecret));
    }

    private String generateRefreshToken(User user) {
        Date issuedDate = new Date();
        Date expirationDate = Date.from(ZonedDateTime.now().plusHours(3).toInstant());

        return JWT.create()
                .withSubject(USER_DETAILS)
                .withClaim(ID, user.getId())
                .withClaim(EMAIL, user.getEmail())
                .withIssuedAt(issuedDate)
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(refreshSecret));
    }

    public JwtTokenResponse generateJWTResponse(User user) {
        return new JwtTokenResponse(generateAccessToken(user),
                generateRefreshToken(user));
    }

    public String validateAccessTokenAndRetrieveClaim(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(accessSecret))
                .withSubject(USER_DETAILS)
                .withIssuer(ISSUER)
                .build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(EMAIL).asString();

        } catch (JWTVerificationException e) {
            throw new JwtException("Invalid access token!");
        }
    }
}
