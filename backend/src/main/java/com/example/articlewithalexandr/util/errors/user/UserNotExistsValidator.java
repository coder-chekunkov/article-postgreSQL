package com.example.articlewithalexandr.util.errors.user;

import com.example.articlewithalexandr.model.User;
import com.example.articlewithalexandr.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Vlad Utts
 */

@Component
@RequiredArgsConstructor
public class UserNotExistsValidator implements Validator {
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        User user = (User) target;

        try {
            userDetailsService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("email", "", "Client with this email already exists!");
    }
}
