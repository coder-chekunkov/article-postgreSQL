package com.example.articlewithalexandr.dto;

/**
 * @author Vlad Utts
 */
public record AddProductRequest(
        Long userId,
        Long productId
) {
}
