package com.example.articlewithalexandr.controller;

import com.example.articlewithalexandr.dto.JwtTokenResponse;
import com.example.articlewithalexandr.dto.UserLoginDto;
import com.example.articlewithalexandr.model.User;
import com.example.articlewithalexandr.service.AuthorizationService;
import com.example.articlewithalexandr.util.errors.MyException;
import com.example.articlewithalexandr.util.errors.user.UserNotExistsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.articlewithalexandr.util.errors.ErrorsReturner.returnErrorsIfContains;

/**
 * @author Vlad Utts
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final UserNotExistsValidator userNotExistsValidator;

    @PostMapping("/register")
    public ResponseEntity<JwtTokenResponse> register(@RequestBody User user, BindingResult bindingResult) {
        userNotExistsValidator.validate(user, bindingResult);
        returnErrorsIfContains(bindingResult, new MyException(""));
        return new ResponseEntity<>(authorizationService.saveUserAndReturnJwtResponse(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody UserLoginDto loginDto) {
        return ResponseEntity.ok(authorizationService.login(loginDto));
    }
}
