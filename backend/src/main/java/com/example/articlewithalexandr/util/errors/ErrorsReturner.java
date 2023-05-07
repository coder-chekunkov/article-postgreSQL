package com.example.articlewithalexandr.util.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vlad Utts
 */

@Slf4j
public class ErrorsReturner {
    private ErrorsReturner() {
    }

    private static String returnTextOfErrors(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.stream()
                .map(error -> error.getField() + " - " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }

    public static void returnErrorsIfContains(BindingResult bindingResult, RuntimeException e) {
        if (bindingResult.hasErrors()) {
            String textOfErrors = returnTextOfErrors(bindingResult);
            log.warn(textOfErrors + e.getStackTrace()[0].toString());
            throw new MyException(textOfErrors);
        }
    }
}
