package com.abaimov.jwtshop.product;

import com.abaimov.jwtshop.product.enums.Gender;
import com.abaimov.jwtshop.product.enums.Materials;
import com.abaimov.jwtshop.product.enums.Type;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String designer;
    private String productName;
    private String size;
    private String color;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Materials materials;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean sale;
    @Min(value = 0)
    private Long price;
    private String filename;

}
