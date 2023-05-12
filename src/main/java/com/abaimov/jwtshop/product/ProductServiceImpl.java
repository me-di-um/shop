package com.abaimov.jwtshop.product;

import com.abaimov.jwtshop.product.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByGender(Gender gender) {
        return productRepository.findProductsByGender(gender);
    }

    @Override
    public List<Product> getProductsByDesigner(String designer) {
        return productRepository.findProductsByDesigner(designer);
    }

    @Override
    public List<Product> getProductsOnSale(Boolean sale) {
        return productRepository.findProductsBySale(sale);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product p = productRepository.findProductById(id).get();
        p.setDesigner(product.getDesigner());
        p.setProductName(product.getProductName());
        p.setSize(product.getSize());
        p.setColor(product.getColor());
        p.setMaterials(product.getMaterials());
        p.setGender(product.getGender());
        p.setPrice(product.getPrice());
        return productRepository.save(p);
    }

    @Override
    public boolean deleteById(Long id) {
        productRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
