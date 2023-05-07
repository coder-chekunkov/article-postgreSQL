package com.example.articlewithalexandr.repository;

import com.example.articlewithalexandr.model.Cart;
import com.example.articlewithalexandr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vlad Utts
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
