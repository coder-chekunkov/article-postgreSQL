package com.example.articlewithalexandr.security;

/**
 * @author Vlad Utts
 */
public class JwtException extends RuntimeException{
    public JwtException(String message) {
        super(message);
    }
}
