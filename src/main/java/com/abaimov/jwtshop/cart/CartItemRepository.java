package com.abaimov.jwtshop.cart;

import com.abaimov.jwtshop.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProduct(Cart cart, Product product);
    List<CartItem> findByCart(Cart cart);
}
