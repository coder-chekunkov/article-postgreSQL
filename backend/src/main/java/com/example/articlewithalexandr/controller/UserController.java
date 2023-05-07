package com.example.articlewithalexandr.controller;

import com.example.articlewithalexandr.model.User;
import com.example.articlewithalexandr.dto.UserEditDTO;
import com.example.articlewithalexandr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vlad Utts
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        User user = userService.findUser(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody UserEditDTO userEditDTO) {
        User user = userService.editUser(id, userEditDTO);
        return ResponseEntity.ok(user);
    }
}
