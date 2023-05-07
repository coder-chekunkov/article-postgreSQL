package com.example.articlewithalexandr.service;

import com.example.articlewithalexandr.dto.UserEditDTO;
import com.example.articlewithalexandr.model.User;
import com.example.articlewithalexandr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Vlad Utts
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow();
    }

    public User editUser(Long id, UserEditDTO userEditDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(userEditDTO.firstName());
        user.setLastName(userEditDTO.lastName());
        user.setMiddleName(userEditDTO.middleName());

        return userRepository.save(user);
    }
}
