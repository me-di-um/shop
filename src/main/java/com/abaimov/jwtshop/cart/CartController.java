package com.abaimov.jwtshop.cart;

import com.abaimov.jwtshop.payload.request.AddToCartRequest;
import com.abaimov.jwtshop.payload.request.AddressRequest;
import com.abaimov.jwtshop.payload.request.RemoveFromCartRequest;
import com.abaimov.jwtshop.user.User;
import com.abaimov.jwtshop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartServiceImpl;
    private final UserService userService;
    private final OrderServiceImpl orderService;

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestHeader("Authorization") String token, @RequestBody AddToCartRequest request) {
        User user = userService.getUserFromToken(token.substring(7));
        cartServiceImpl.addToCart(user, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeFromCart(@RequestHeader("Authorization") String token, @RequestBody RemoveFromCartRequest request) {
        User user = userService.getUserFromToken(token.substring(7));
        cartServiceImpl.removeFromCart(user, request.getProductId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String token) {
        User user = userService.getUserFromToken(token.substring(7));
        Cart cart = cartServiceImpl.getCart(user);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestHeader("Authorization") String token, @RequestBody AddressRequest addressRequest) {
        User user = userService.getUserFromToken(token.substring(7));
        Order order;
        if (addressRequest != null) {
            order = orderService.createOrder(user, addressRequest.getAddress());
        } else {
            order = orderService.createOrder(user, user.getAddress());
        }
        return ResponseEntity.ok(order);
    }
}


