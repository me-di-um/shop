package com.abaimov.jwtshop.cart;

import com.abaimov.jwtshop.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public Order createOrder(User user, String address) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new EntityNotFoundException("Cart not found");
        }
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        if (cartItems == null || cartItems.isEmpty()) {
            throw new EntityNotFoundException("No items in cart");
        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(cartItems.stream()
                .map(cartItem -> {
                    OrderItems orderItem = new OrderItems();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList()));
        order.setOrderTotal(cart.getTotalPrice());
        order.setAddress(address);
        orderRepository.save(order);
        cartRepository.delete(cart);
        return order;
    }
}