package com.abaimov.jwtshop.product;


import com.abaimov.jwtshop.product.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);

    List<Product> findProductsByGender(Gender gender);

    List<Product> findProductsByDesigner(String designer);

    List<Product> findProductsBySale(Boolean sale);

    @Query("SELECT p FROM Product p WHERE lower(p.productName) LIKE lower(concat('%', :query, '%'))" +
            " OR lower(p.designer) LIKE lower(concat('%', :query, '%'))" +
            " OR lower(p.type) LIKE lower(concat('%', :query, '%'))")
    List<Product> search(@Param("query") String query);
}
