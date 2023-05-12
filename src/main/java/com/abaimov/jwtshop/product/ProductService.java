package com.abaimov.jwtshop.product;


import com.abaimov.jwtshop.product.enums.Gender;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByGender(Gender gender);

    List<Product> getProductsByDesigner(String designer);

    List<Product> getProductsOnSale(Boolean sale);

    Product findById(Long id);

    Product updateProduct(Long id, Product product);

    boolean deleteById(Long id);
}
