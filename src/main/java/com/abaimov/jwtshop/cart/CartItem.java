package com.abaimov.jwtshop.cart;

import com.abaimov.jwtshop.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @Min(value = 0)
    private int quantity;
    private Long price;
    @ManyToOne
    @JsonIgnore
    private Cart cart;
    @ManyToOne
    @JsonIgnore
    private Order order;

    public Long getProductId(Product product) {
        return product.getId();
    }
}
