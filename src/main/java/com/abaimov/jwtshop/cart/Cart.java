package com.abaimov.jwtshop.cart;

import com.abaimov.jwtshop.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    private Long totalPrice;
    @OneToOne
    private User user;
}
