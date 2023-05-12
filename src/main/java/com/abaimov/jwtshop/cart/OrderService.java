package com.abaimov.jwtshop.cart;


import com.abaimov.jwtshop.user.User;

public interface OrderService {
    Order createOrder(User user, String address);
}
