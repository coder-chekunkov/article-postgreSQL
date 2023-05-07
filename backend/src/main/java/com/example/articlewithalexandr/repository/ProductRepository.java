package com.example.articlewithalexandr.repository;

import com.example.articlewithalexandr.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vlad Utts
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
