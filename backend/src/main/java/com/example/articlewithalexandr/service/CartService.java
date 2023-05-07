package com.example.articlewithalexandr.service;

import com.example.articlewithalexandr.dto.AddProductRequest;
import com.example.articlewithalexandr.model.Cart;
import com.example.articlewithalexandr.model.Product;
import com.example.articlewithalexandr.model.User;
import com.example.articlewithalexandr.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Vlad Utts
 */
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public void addProduct(AddProductRequest request) {
        Cart cart = find(request.userId());
        Product product = productService.findProduct(request.productId());
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    public Cart find(Long userId) {
        User user = userService.findUser(userId);
        return cartRepository.findByUser(user);
    }
}
