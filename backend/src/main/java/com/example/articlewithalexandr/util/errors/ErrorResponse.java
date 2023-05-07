package com.example.articlewithalexandr.util.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vlad Utts
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private long timestamp;
}
