package com.abaimov.jwtshop.cart;



import com.abaimov.jwtshop.product.Product;
import com.abaimov.jwtshop.product.ProductRepository;
import com.abaimov.jwtshop.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public void addToCart(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItemRepository.save(cartItem);
    }
    @Override
    public void removeFromCart(User user, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Cart cart = cartRepository.findByUser(user);
        if (cart != null) {
            CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product);
            if (cartItem != null) {
                cartItemRepository.delete(cartItem);
            }
        }
    }
    @Override
    public Cart getCart(User user) {
        Cart cart = cartRepository.findByUser(user);
        cart.setTotalPrice(calculateTotalPrice(user));
        cart = cartRepository.save(cart);
        return cart;
    }
    @Override
    public long calculateTotalPrice(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart != null) {
            long totalPrice = 0;
            for (CartItem cartItem : cart.getCartItems()) {
                long itemPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                totalPrice += itemPrice;
            }
            return totalPrice;
        } else {
            return 0;
        }
    }
}

