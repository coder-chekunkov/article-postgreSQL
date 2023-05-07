package com.example.articlewithalexandr.dto;

/**
 * @author Vlad Utts
 */
public record JwtTokenResponse(
        String accessToken,
        String refreshToken
) {
}
