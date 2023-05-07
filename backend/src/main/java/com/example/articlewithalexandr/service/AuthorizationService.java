package com.example.articlewithalexandr.service;

import com.example.articlewithalexandr.dto.JwtTokenResponse;
import com.example.articlewithalexandr.dto.UserLoginDto;
import com.example.articlewithalexandr.model.Cart;
import com.example.articlewithalexandr.model.Product;
import com.example.articlewithalexandr.model.User;
import com.example.articlewithalexandr.repository.CartRepository;
import com.example.articlewithalexandr.repository.UserRepository;
import com.example.articlewithalexandr.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Vlad Utts
 */

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public JwtTokenResponse saveUserAndReturnJwtResponse(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Cart cart = new Cart(user, new ArrayList<>());
        cartRepository.save(cart);

        return jwtUtil.generateJWTResponse(user);
    }

    public JwtTokenResponse login(UserLoginDto loginDto) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        authenticationManager.authenticate(auth);
        return jwtUtil.generateJWTResponse(userRepository.findByEmail(loginDto.email()).orElseThrow());
    }
}
