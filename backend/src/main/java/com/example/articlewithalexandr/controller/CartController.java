package com.example.articlewithalexandr.controller;

import com.example.articlewithalexandr.dto.AddProductRequest;
import com.example.articlewithalexandr.model.Product;
import com.example.articlewithalexandr.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vlad Utts
 */
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody AddProductRequest request) {
        cartService.addProduct(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public List<Product> find(@PathVariable Long userId) {
        return cartService.find(userId).getProducts();
    }
}
