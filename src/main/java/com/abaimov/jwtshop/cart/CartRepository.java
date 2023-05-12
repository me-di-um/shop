package com.abaimov.jwtshop.cart;

import com.abaimov.jwtshop.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
