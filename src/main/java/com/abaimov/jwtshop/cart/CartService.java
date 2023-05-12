package com.abaimov.jwtshop.cart;


import com.abaimov.jwtshop.user.User;

public interface CartService {
    void addToCart(User user, Long productId, int quantity);
    void removeFromCart(User user, Long productId);
    Cart getCart(User user);
    long calculateTotalPrice(User user);
}
