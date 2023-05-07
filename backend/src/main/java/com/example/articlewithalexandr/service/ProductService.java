package com.example.articlewithalexandr.service;

import com.example.articlewithalexandr.model.Product;
import com.example.articlewithalexandr.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vlad Utts
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
