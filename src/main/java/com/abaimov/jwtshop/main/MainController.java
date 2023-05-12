package com.abaimov.jwtshop.main;

import com.abaimov.jwtshop.product.Product;
import com.abaimov.jwtshop.product.ProductService;
import com.abaimov.jwtshop.product.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/men")
    public ResponseEntity<List<Product>> getMaleProducts() {
        return ResponseEntity.ok(productService.getProductsByGender(Gender.MALE));
    }

    @GetMapping("/women")
    public ResponseEntity<List<Product>> getFemaleProducts() {
        return ResponseEntity.ok(productService.getProductsByGender(Gender.FEMALE));
    }

    @GetMapping("/everything-else")
    public ResponseEntity<List<Product>> getNonGenderedProducts() {
        return ResponseEntity.ok(productService.getProductsByGender(Gender.ANY));
    }

    @GetMapping("/sale")
    public ResponseEntity<List<Product>> getProductsOnSale() {
        return ResponseEntity.ok(productService.getProductsOnSale(true));
    }
}
