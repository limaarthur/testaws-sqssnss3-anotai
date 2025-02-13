package com.ignitec.teste_anotai.services;

import com.ignitec.teste_anotai.domain.category.Category;
import com.ignitec.teste_anotai.domain.category.exceptions.CategoryNotFoundException;
import com.ignitec.teste_anotai.domain.product.Product;
import com.ignitec.teste_anotai.domain.product.ProductDTO;
import com.ignitec.teste_anotai.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;
    private ProductRepository productRepository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    public Product create(ProductDTO productDTO) {
        Category category = this.categoryService.getById(productDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productDTO);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return newProduct;
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (productDTO.categoryId() != null) {
            this.categoryService.getById(productDTO.categoryId())
                    .ifPresent(product::setCategory);
        }

        if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if (!(productDTO.price() == null)) product.setPrice((productDTO.price()));

        this.productRepository.save(product);

        return product;
    }

    public void delete(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.productRepository.delete(product);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }
}
